package br.com.ecommerce.adapters.mapper.product;

import br.com.ecommerce.adapters.mapper.OutputTestUtils;
import br.com.ecommerce.core.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductOutputDataTest {

  @Test
  public void shouldConvertFromEntity() {
    Product product = OutputTestUtils.buildProduct();
    ProductOutputData outputData = new ProductOutputData().fromEntity(product);

    Assertions.assertEquals(product.getCode(), outputData.getId());
    Assertions.assertEquals(product.getName(), outputData.getName());
    Assertions.assertEquals(product.getDescription(), outputData.getDescription());
    Assertions.assertEquals(product.getPrice(), outputData.getPrice());
    Assertions.assertEquals(product.getImage(), outputData.getImage());
    Assertions.assertEquals(product.getRate(), outputData.getRate());
    Assertions.assertEquals(product.getMaxParcels(), outputData.getMaxParcels());
    Assertions.assertEquals(product.getStock(), outputData.getStock());
  }

}