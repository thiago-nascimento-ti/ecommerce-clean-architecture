package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.InputDataTestUtils;
import br.com.ecommerce.core.entity.OrderItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderItemInputDataTest {

  @Test
  public void shouldAdapterConvertToEntity() {
    OrderItemInputData inputData = InputDataTestUtils.buildOrderItemInputData();
    ProductInputData productInputData = inputData.getProduct();

    OrderItem item = inputData.toEntity();

    Assertions.assertEquals(inputData.getAmount(), item.getAmount());
    Assertions.assertEquals(productInputData.getId(), item.getProduct().getCode());
    Assertions.assertEquals(productInputData.getName(), item.getProduct().getName());
    Assertions.assertEquals(productInputData.getDescription(), item.getProduct().getDescription());
    Assertions.assertEquals(productInputData.getPrice(), item.getProduct().getPrice());
    Assertions.assertEquals(productInputData.getImage(), item.getProduct().getImage());
    Assertions.assertNull(item.getProduct().getRate());
    Assertions.assertNull(item.getProduct().getMaxParcels());
    Assertions.assertNull(item.getProduct().getStock());
  }

}