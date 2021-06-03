package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.InputDataTestUtils;
import br.com.ecommerce.core.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductInputDataTest {

  @Test
  public void shouldConvertToEntity() {
    long code = 1123123123L;
    ProductInputData inputData = InputDataTestUtils.buildProductInputData(code);

    Product product = inputData.toEntity();

    Assertions.assertEquals(code, product.getCode());
    Assertions.assertEquals(inputData.getName(), product.getName());
    Assertions.assertEquals(inputData.getDescription(), product.getDescription());
    Assertions.assertEquals(inputData.getPrice(), product.getPrice());
    Assertions.assertEquals(inputData.getImage(), product.getImage());
    Assertions.assertNull(product.getRate());
    Assertions.assertNull(product.getMaxParcels());
    Assertions.assertNull(product.getStock());
  }

}