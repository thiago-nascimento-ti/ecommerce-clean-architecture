package br.com.ecommerce.adapters.mapper.product;

import br.com.ecommerce.core.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateProductInputDataTest {

  @Test
  public void shouldConvertToEntity() {
    long code = 1123123123L;
    String name = "Product test";
    String description = "Product test description";
    double price = 20.23;
    String image = "url";
    int rate = 5;
    int maxParcels = 10;
    int stock = 5;

    CreateProductInputData inputData = new CreateProductInputData();
    inputData.setId(code);
    inputData.setName(name);
    inputData.setDescription(description);
    inputData.setPrice(price);
    inputData.setImage(image);
    inputData.setRate(rate);
    inputData.setMaxParcels(maxParcels);
    inputData.setStock(stock);

    Product product = inputData.toEntity();

    Assertions.assertEquals(code, product.getCode());
    Assertions.assertEquals(name, product.getName());
    Assertions.assertEquals(description, product.getDescription());
    Assertions.assertEquals(price, product.getPrice());
    Assertions.assertEquals(image, product.getImage());
    Assertions.assertEquals(rate, product.getRate());
    Assertions.assertEquals(maxParcels, product.getMaxParcels());
    Assertions.assertEquals(stock, product.getStock());
  }

}