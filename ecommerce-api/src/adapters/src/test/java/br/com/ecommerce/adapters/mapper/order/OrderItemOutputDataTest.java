package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.OutputTestUtils;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderItemOutputDataTest {

  @Test
  public void shouldConvertFromEntity() {
    OrderItem item = OutputTestUtils.buildOrderItem();
    Product product = item.getProduct();

    OrderItemOutputData outputData = new OrderItemOutputData().fromEntity(item);

    Assertions.assertEquals(item.getAmount(), outputData.getAmount());
    Assertions.assertEquals(product.getCode(), outputData.getProduct().getId());
    Assertions.assertEquals(product.getName(), outputData.getProduct().getName());
    Assertions.assertEquals(product.getDescription(), outputData.getProduct().getDescription());
    Assertions.assertEquals(product.getPrice(), outputData.getProduct().getPrice());
    Assertions.assertEquals(product.getImage(), outputData.getProduct().getImage());
    Assertions.assertNull(outputData.getProduct().getStock());
    Assertions.assertNull(outputData.getProduct().getMaxParcels());
    Assertions.assertNull(outputData.getProduct().getRate());
  }

}