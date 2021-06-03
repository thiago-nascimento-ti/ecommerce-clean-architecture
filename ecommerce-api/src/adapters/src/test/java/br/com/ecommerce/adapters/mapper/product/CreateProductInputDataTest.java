package br.com.ecommerce.adapters.mapper.product;

import br.com.ecommerce.adapters.mapper.InputDataTestUtils;
import br.com.ecommerce.core.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateProductInputDataTest {

  @Test
  public void shouldConvertToEntity() {
    long code = 1123123123L;
    CreateProductInputData inputData = InputDataTestUtils.buildCreateProductInputData(code);
    Product product = inputData.toEntity();

    Assertions.assertEquals(inputData.getId(), product.getCode());
    Assertions.assertEquals(inputData.getName(), product.getName());
    Assertions.assertEquals(inputData.getDescription(), product.getDescription());
    Assertions.assertEquals(inputData.getPrice(), product.getPrice());
    Assertions.assertEquals(inputData.getImage(), product.getImage());
    Assertions.assertEquals(inputData.getRate(), product.getRate());
    Assertions.assertEquals(inputData.getMaxParcels(), product.getMaxParcels());
    Assertions.assertEquals(inputData.getStock(), product.getStock());
  }

}