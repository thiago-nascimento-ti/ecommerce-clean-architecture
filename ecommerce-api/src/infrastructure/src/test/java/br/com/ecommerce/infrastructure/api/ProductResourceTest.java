package br.com.ecommerce.infrastructure.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.ecommerce.adapters.mapper.product.CreateProductInputData;
import br.com.ecommerce.adapters.mapper.product.ProductOutputData;
import br.com.ecommerce.core.entity.Paged;
import br.com.ecommerce.core.service.ProductService;
import br.com.ecommerce.infrastructure.Application;
import br.com.ecommerce.infrastructure.testcontainers.mongodb.MongoContainer;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
public class ProductResourceTest extends MongoContainer {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ProductService service;

  @Autowired
  private Gson gson;

  @Autowired
  public ProductResourceTest(MongoTemplate mongoTemplate) {
    super(mongoTemplate);
  }

  @Test
  public void shouldReturnCreatedAndLocationWhenCreateProduct()
      throws Exception {
    long code = 5727542951L;
    CreateProductInputData inputData = buildCreateProductInputData(code);
    ProductOutputData outputData = convertToProductOutputData(inputData);

    mvc.perform(post("/v1/products")
        .content(gson.toJson(inputData))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(header().string("location", "http://localhost/v1/products/5727542951"));
  }

  @Test
  public void shouldReturnConflictWhenTryToCreateProductAlreadyExists()
      throws Exception {
    long code = 5727542951L;
    CreateProductInputData inputData = buildCreateProductInputData(code);

    service.save(inputData.toEntity());

    mvc.perform(post("/v1/products")
        .content(gson.toJson(inputData))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().string(expectedError("Product code 5727542951 already exists")));
  }

  @Test
  public void shouldReturnOkAndProductWhenFindByCode()
      throws Exception {
    long code = 5727542951L;
    CreateProductInputData inputData = buildCreateProductInputData(code);
    ProductOutputData outputData = convertToProductOutputData(inputData);

    service.save(inputData.toEntity());

    mvc.perform(get("/v1/products/"+code))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().json(gson.toJson(outputData), false));
  }

  @Test
  public void shouldReturnNotFoundWhenFindByCodeAndProductDoesNotExists()
      throws Exception {
    long code = 5727542951L;
    CreateProductInputData inputData = buildCreateProductInputData(code);

    mvc.perform(get("/v1/products/"+code))
        .andExpect(status().isNotFound())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content()
            .string(expectedError("Product code 5727542951 not found")));
  }

  @Test
  public void shouldReturnOkWithAllProductsWithoutPagination()
      throws Exception {
    Paged<ProductOutputData> paged = preparePagedOutputData(1, 100);

    mvc.perform(get("/v1/products"))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(header().string("x-total-count", String.valueOf(paged.getTotalItems())))
        .andExpect(content()
            .json(gson.toJson(paged.getItems()), false));
  }

  @Test
  public void shouldReturnOkAndReturnFirstPage()
      throws Exception {
    int page = 1;
    int limit = 2;
    Paged<ProductOutputData> paged = preparePagedOutputData(page, limit);

    mvc.perform(get("/v1/products?_limit="+limit+"&_page="+page))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(header().string("x-total-count", String.valueOf(paged.getTotalItems())))
        .andExpect(content()
            .json(gson.toJson(paged.getItems()), false));
  }

  @Test
  public void shouldReturnOkAndReturnSecondPage()
      throws Exception {
    int page = 2;
    int limit = 2;
    Paged<ProductOutputData> paged = preparePagedOutputData(page, limit);

    mvc.perform(get("/v1/products?_limit="+limit+"&_page="+page))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(header().string("x-total-count", String.valueOf(paged.getTotalItems())))
        .andExpect(content()
            .json(gson.toJson(paged.getItems()), false));
  }


  @Test
  public void shouldReturnOkAndReturnThirdPage()
      throws Exception {
    int page = 3;
    int limit = 2;
    Paged<ProductOutputData> paged = preparePagedOutputData(page, limit);

    mvc.perform(get("/v1/products?_limit="+limit+"&_page="+page))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(header().string("x-total-count", String.valueOf(paged.getTotalItems())))
        .andExpect(content()
            .json(gson.toJson(paged.getItems()), false));
  }

  private Paged<ProductOutputData> preparePagedOutputData(int page, int limit) {
    List<CreateProductInputData> inputDataList = new ArrayList<>();
    inputDataList.add(buildCreateProductInputData(5727542951L));
    inputDataList.add(buildCreateProductInputData(7113984583L));
    inputDataList.add(buildCreateProductInputData(3888485474L));
    inputDataList.add(buildCreateProductInputData(4770262101L));
    inputDataList.add(buildCreateProductInputData(5645637958L));

    inputDataList
        .stream()
        .map(inputData -> inputData.toEntity())
        .forEach(service::save);

    int skip = (page * limit) - limit;
    List<ProductOutputData> outputData = inputDataList
        .stream()
        .skip(skip)
        .limit(limit)
        .map(this::convertToProductOutputData)
        .collect(Collectors.toList());

    return new Paged(outputData, inputDataList.size());
  }

  private ProductOutputData convertToProductOutputData(CreateProductInputData inputData) {
    ProductOutputData productOutputData = new ProductOutputData();
    productOutputData.fromEntity(inputData.toEntity());
    return productOutputData;
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
    inputData.setStock(100);
    return inputData;
  }

  private String expectedError(String content) {
    return "{\"message\":\""+content+"\"}";
  }

}