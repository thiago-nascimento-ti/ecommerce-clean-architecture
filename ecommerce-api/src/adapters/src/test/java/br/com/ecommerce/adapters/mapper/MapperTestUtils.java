package br.com.ecommerce.adapters.mapper;

import br.com.ecommerce.adapters.mapper.order.CreateOrderInputData;
import br.com.ecommerce.adapters.mapper.order.CreditCardInputData;
import br.com.ecommerce.adapters.mapper.order.OrderItemInputData;
import br.com.ecommerce.adapters.mapper.order.ProductInputData;
import java.util.Arrays;

public class MapperTestUtils {

  public static CreateOrderInputData buildCreateOrderInputData() {
    CreateOrderInputData createOrderInputData = new CreateOrderInputData();
    createOrderInputData.setPayable(30);
    createOrderInputData.setItemsAmount(10);
    createOrderInputData.setItems(Arrays.asList(buildOrderItemInputData()));
    createOrderInputData.setCreditCard(buildCreditCardInputData());
    return createOrderInputData;
  }

  public static OrderItemInputData buildOrderItemInputData() {
    return buildOrderItemInputData(1123123123L);
  }

  public static  OrderItemInputData buildOrderItemInputData(long code) {
    ProductInputData productInputData = new ProductInputData();
    productInputData.setId(code);
    productInputData.setName("Product test");
    productInputData.setDescription("Product test description");
    productInputData.setPrice(20.23);
    productInputData.setImage("url");

    OrderItemInputData orderItemInputData = new OrderItemInputData();
    orderItemInputData.setAmount(3);
    orderItemInputData.setProduct(productInputData);

    return orderItemInputData;
  }

  public static CreditCardInputData buildCreditCardInputData() {
    CreditCardInputData inputData = new CreditCardInputData();
    inputData.setCardNumber("1111111111111111");
    inputData.setCvv("111");
    inputData.setName("John Doe");
    inputData.setValidateDate("2070/12");
    inputData.setParcelAmount(2);
    return inputData;
  }

}