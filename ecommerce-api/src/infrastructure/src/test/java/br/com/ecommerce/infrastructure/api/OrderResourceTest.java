package br.com.ecommerce.infrastructure.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.ecommerce.adapters.mapper.order.CreateOrderInputData;
import br.com.ecommerce.adapters.mapper.order.CreditCardInputData;
import br.com.ecommerce.adapters.mapper.order.OrderItemInputData;
import br.com.ecommerce.adapters.mapper.order.OrderOutputData;
import br.com.ecommerce.adapters.mapper.order.ProductInputData;
import br.com.ecommerce.adapters.mapper.product.CreateProductInputData;
import br.com.ecommerce.adapters.mapper.product.ProductOutputData;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.entity.Paged;
import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.exception.InsufficientStockException;
import br.com.ecommerce.core.service.OrderService;
import br.com.ecommerce.core.service.ProductService;
import br.com.ecommerce.infrastructure.Application;
import br.com.ecommerce.infrastructure.exception.InsufficientStockError;
import br.com.ecommerce.infrastructure.testcontainers.mongodb.MongoContainer;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
    locations = "classpath:application.properties")
public class OrderResourceTest extends MongoContainer {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ProductService productService;

  @Autowired
  private OrderService orderService;

  @Autowired
  private Gson gson;

  @Autowired
  public OrderResourceTest(MongoTemplate mongoTemplate) {
    super(mongoTemplate);
  }

  @Test
  public void shouldCreateNewOrder()
      throws Exception {
    List<CreateProductInputData> createdProducts = prepareProductsOnDatabase();

    CreditCardInputData creditCardInputData = buildCreditCardInputData(true);
    List<OrderItemInputData> items = createdProducts
        .stream()
        .map(this::buildOrderItemInputData)
        .collect(Collectors.toList());
    CreateOrderInputData createOrderInputData = buildCreateOrderInputData(items, creditCardInputData);

    mvc.perform(post("/v1/orders")
        .content(gson.toJson(createOrderInputData))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void shouldReturnOkAndOrderWhenFindById()
      throws Exception {
    List<CreateProductInputData> createdProducts = prepareProductsOnDatabase();

    CreditCardInputData creditCardInputData = buildCreditCardInputData(true);
    List<OrderItemInputData> items = createdProducts
        .stream()
        .map(this::buildOrderItemInputData)
        .collect(Collectors.toList());
    CreateOrderInputData createOrderInputData = buildCreateOrderInputData(items, creditCardInputData);

    Order order = createOrderInputData.toEntity();
    UUID id = orderService.create(order).getId();
    OrderOutputData expectedBody = new OrderOutputData().fromEntity(order);

    mvc.perform(get("/v1/orders/"+id))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().json(gson.toJson(expectedBody), false));
  }

  @Test
  public void shouldReturnPaymentRequiredStatusWhenCreditCardIsNotValid()
      throws Exception {
    List<CreateProductInputData> createdProducts = prepareProductsOnDatabase();

    CreditCardInputData creditCardInputData = buildCreditCardInputData(false);
    List<OrderItemInputData> items = createdProducts
        .stream()
        .map(this::buildOrderItemInputData)
        .collect(Collectors.toList());
    CreateOrderInputData createOrderInputData = buildCreateOrderInputData(items, creditCardInputData);

    mvc.perform(post("/v1/orders")
        .content(gson.toJson(createOrderInputData))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isPaymentRequired());
  }

  @Test
  public void shouldReturnBadRequestAndBodyWhenDoesNotHaveEnoughStock()
      throws Exception {
    List<CreateProductInputData> createdProducts = prepareProductsOnDatabase();

    CreditCardInputData creditCardInputData = buildCreditCardInputData(true);
    List<OrderItemInputData> items = createdProducts
        .stream()
        .map(item -> buildOrderItemInputData(10, item))
        .collect(Collectors.toList());
    CreateOrderInputData createOrderInputData = buildCreateOrderInputData(items, creditCardInputData);

    List<Long> insufficientStockCodes =createdProducts
        .stream()
        .mapToLong(CreateProductInputData::getId)
        .boxed()
        .collect(Collectors.toList());
    InsufficientStockException error = new InsufficientStockException(insufficientStockCodes);
    InsufficientStockError expectedBody = new InsufficientStockError(error);

    mvc.perform(post("/v1/orders")
        .content(gson.toJson(createOrderInputData))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content()
            .json(gson.toJson(expectedBody), false));
  }

  private CreateOrderInputData buildCreateOrderInputData(List<OrderItemInputData> items, CreditCardInputData creditCardInputData) {
    CreateOrderInputData createOrderInputData = new CreateOrderInputData();
    createOrderInputData.setCreditCard(creditCardInputData);
    createOrderInputData.setItems(items);
    createOrderInputData.setPayable(getOrderPayable(items));
    createOrderInputData.setItemsAmount(getItemsAmount(items));

    return createOrderInputData;
  }

  private int getItemsAmount(List<OrderItemInputData> items) {
    return items
        .stream()
        .map(OrderItemInputData::getAmount)
        .reduce(0, Integer::sum);
  }

  private double getOrderPayable(List<OrderItemInputData> items) {
    return items
        .stream()
        .map(item -> item.getProduct().getPrice() * item.getAmount())
        .reduce(0d, Double::sum);
  }

  private OrderItemInputData buildOrderItemInputData(CreateProductInputData createdProductInputData) {
    return buildOrderItemInputData(createdProductInputData.getStock(), createdProductInputData);
  }

  private OrderItemInputData buildOrderItemInputData(int amount, CreateProductInputData createdProductInputData) {
    Product product = createdProductInputData.toEntity();

    ProductInputData productInputData = new ProductInputData();
    productInputData.setId(product.getCode());
    productInputData.setName(product.getName());
    productInputData.setDescription(product.getDescription());
    productInputData.setPrice(product.getPrice());
    productInputData.setImage(product.getImage());

    OrderItemInputData orderItemInputData = new OrderItemInputData();
    orderItemInputData.setProduct(productInputData);
    orderItemInputData.setAmount(amount);

    return orderItemInputData;
  }

  private CreditCardInputData buildCreditCardInputData(boolean valid) {
    CreditCardInputData creditCardInputData = new CreditCardInputData();
    creditCardInputData.setCardNumber("1111111111111111");
    creditCardInputData.setCvv(valid ? "111" : "123");
    creditCardInputData.setName("Credit card test");
    creditCardInputData.setParcelAmount(12);
    creditCardInputData.setValidateDate("2070/05");

    return creditCardInputData;
  }

  private List<CreateProductInputData> prepareProductsOnDatabase() {
    List<CreateProductInputData> inputDataList = new ArrayList<>();
    inputDataList.add(buildCreateProductInputData(5727542951L));
    inputDataList.add(buildCreateProductInputData(7113984583L));
    inputDataList.add(buildCreateProductInputData(3888485474L));
    inputDataList.add(buildCreateProductInputData(4770262101L));
    inputDataList.add(buildCreateProductInputData(5645637958L));

    inputDataList
        .stream()
        .map(inputData -> inputData.toEntity())
        .forEach(productService::save);

    return inputDataList;
  }

  private CreateProductInputData buildCreateProductInputData(long code) {
    CreateProductInputData inputData = new CreateProductInputData();
    inputData.setId(code);
    inputData.setName("Product Test");
    inputData.setDescription("Description Test");
    inputData.setPrice(4149);
    inputData.setImage("url");
    inputData.setRate(5);
    inputData.setMaxParcels(12);
    inputData.setStock(5);
    return inputData;
  }

}