package br.com.ecommerce.adapters.mapper;

import br.com.ecommerce.adapters.mapper.order.CreateOrderInputData;
import br.com.ecommerce.adapters.mapper.order.CreditCardInputData;
import br.com.ecommerce.adapters.mapper.order.OrderItemInputData;
import br.com.ecommerce.adapters.mapper.order.ProductInputData;
import br.com.ecommerce.adapters.mapper.product.CreateProductInputData;
import java.util.Arrays;

public class InputDataTestUtils {

  public static CreateOrderInputData buildCreateOrderInputData() {
    CreateOrderInputData createOrderInputData = new CreateOrderInputData();
    createOrderInputData.setItems(Arrays.asList(buildOrderItemInputData()));
    createOrderInputData.setCreditCard(buildCreditCardInputData());
    return createOrderInputData;
  }

  public static ProductInputData buildProductInputData(long code) {
    ProductInputData productInputData = new ProductInputData();
    productInputData.setId(code);
    productInputData.setName("Product test");
    productInputData.setDescription("Product test description");
    productInputData.setPrice(20.23);
    productInputData.setImage("url");
    return productInputData;
  }

  public static CreateProductInputData buildCreateProductInputData(long code) {
    CreateProductInputData createProductInputData = new CreateProductInputData();
    createProductInputData.setId(code);
    createProductInputData.setName("Product test");
    createProductInputData.setDescription("Product test description");
    createProductInputData.setPrice(20.23);
    createProductInputData.setImage("url");
    createProductInputData.setStock(2);
    createProductInputData.setMaxParcels(10);
    createProductInputData.setRate(5D);
    return createProductInputData;
  }

  public static OrderItemInputData buildOrderItemInputData() {
    return buildOrderItemInputData(1123123123L);
  }

  public static OrderItemInputData buildOrderItemInputData(long code) {
    ProductInputData productInputData = buildProductInputData(code);
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
    inputData.setValidateDate("2070-12");
    inputData.setParcelAmount(2);
    return inputData;
  }

}