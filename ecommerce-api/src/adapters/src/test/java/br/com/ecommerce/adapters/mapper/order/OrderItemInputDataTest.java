package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.core.entity.OrderItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderItemInputDataTest {

  @Test
  public void shouldAdapterConvertToEntity() {
    long code = 1123123123L;
    String name = "Product test";
    String description = "Product test description";
    double price = 20.23;
    String image = "url";
    int amount = 3;

    ProductInputData productInputData = new ProductInputData();
    productInputData.setId(code);
    productInputData.setName(name);
    productInputData.setDescription(description);
    productInputData.setPrice(price);
    productInputData.setImage(image);

    OrderItemInputData inputData = new OrderItemInputData();
    inputData.setAmount(amount);
    inputData.setProduct(productInputData);

    OrderItem item = inputData.toEntity();

    Assertions.assertEquals(amount, item.getAmount());
    Assertions.assertEquals(code, item.getProduct().getCode());
    Assertions.assertEquals(name, item.getProduct().getName());
    Assertions.assertEquals(description, item.getProduct().getDescription());
    Assertions.assertEquals(price, item.getProduct().getPrice());
    Assertions.assertEquals(image, item.getProduct().getImage());
    Assertions.assertNull(item.getProduct().getRate());
    Assertions.assertNull(item.getProduct().getMaxParcels());
    Assertions.assertNull(item.getProduct().getStock());
  }

}