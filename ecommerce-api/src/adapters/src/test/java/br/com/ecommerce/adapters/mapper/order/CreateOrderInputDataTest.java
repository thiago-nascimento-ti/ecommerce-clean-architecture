package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.MapperTestUtils;
import br.com.ecommerce.core.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateOrderInputDataTest {

  @Test
  public void shouldConvertToEntity() {
    CreateOrderInputData inputData = MapperTestUtils.buildCreateOrderInputData();

    Order order = inputData.toEntity();

    Assertions.assertNull(order.getId());
    Assertions.assertEquals(inputData.getPayable(), order.getPayable());
    Assertions.assertEquals(inputData.getItemsAmount(), order.getItemsAmount());
    Assertions.assertEquals(inputData.getItems().size(), order.getItems().size());
    Assertions.assertNotNull(inputData.getCreditCard());
  }

}