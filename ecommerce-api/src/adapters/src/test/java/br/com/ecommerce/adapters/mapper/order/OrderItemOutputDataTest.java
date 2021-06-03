package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderItemOutputDataTest {

  @Test
  public void shouldConvertFromEntity() {
    long code = 1123123123L;
    String name = "Product test";
    String description = "Product test description";
    double price = 20.23;
    String image = "url";
    int amount = 5;

    Product product = new Product(code, name, description, price, image, null, null, null);
    OrderItem item = new OrderItem(product, amount);

    OrderItemOutputData outputData = new OrderItemOutputData().fromEntity(item);

    Assertions.assertEquals(amount, outputData.getAmount());
    Assertions.assertEquals(code, outputData.getProduct().getId());
    Assertions.assertEquals(name, outputData.getProduct().getName());
    Assertions.assertEquals(description, outputData.getProduct().getDescription());
    Assertions.assertEquals(price, outputData.getProduct().getPrice());
    Assertions.assertEquals(image, outputData.getProduct().getImage());
    Assertions.assertNull(outputData.getProduct().getStock());
    Assertions.assertNull(outputData.getProduct().getMaxParcels());
    Assertions.assertNull(outputData.getProduct().getRate());
  }

}