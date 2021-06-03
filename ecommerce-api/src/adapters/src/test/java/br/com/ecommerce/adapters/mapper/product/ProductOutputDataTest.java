package br.com.ecommerce.adapters.mapper.product;

import br.com.ecommerce.core.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductOutputDataTest {

  @Test
  public void shouldConvertFromEntity() {
    long code = 1123123123L;
    String name = "Product test";
    String description = "Product test description";
    double price = 20.23;
    String image = "url";
    int rate = 5;
    int maxParcels = 10;
    int stock = 5;

    Product product = new Product(code, name, description, price, image, rate, maxParcels, stock);
    ProductOutputData outputData = new ProductOutputData().fromEntity(product);

    Assertions.assertEquals(code, outputData.getId());
    Assertions.assertEquals(name, outputData.getName());
    Assertions.assertEquals(description, outputData.getDescription());
    Assertions.assertEquals(price, outputData.getPrice());
    Assertions.assertEquals(image, outputData.getImage());
    Assertions.assertEquals(rate, outputData.getRate());
    Assertions.assertEquals(maxParcels, outputData.getMaxParcels());
    Assertions.assertEquals(stock, outputData.getStock());
  }

}