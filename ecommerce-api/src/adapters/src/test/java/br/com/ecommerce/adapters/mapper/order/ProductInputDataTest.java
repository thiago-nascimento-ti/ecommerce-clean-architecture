package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.core.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductInputDataTest {

  @Test
  public void shouldConvertToEntity() {
    long code = 1123123123L;
    String name = "Product test";
    String description = "Product test description";
    double price = 20.23;
    String image = "url";

    ProductInputData inputData = new ProductInputData();
    inputData.setId(code);
    inputData.setName(name);
    inputData.setDescription(description);
    inputData.setPrice(price);
    inputData.setImage(image);

    Product product = inputData.toEntity();

    Assertions.assertEquals(code, product.getCode());
    Assertions.assertEquals(name, product.getName());
    Assertions.assertEquals(description, product.getDescription());
    Assertions.assertEquals(price, product.getPrice());
    Assertions.assertEquals(image, product.getImage());
    Assertions.assertNull(product.getRate());
    Assertions.assertNull(product.getMaxParcels());
    Assertions.assertNull(product.getStock());
  }

}