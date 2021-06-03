package br.com.ecommerce.adapters.repository.mongo.model.adapter;

import br.com.ecommerce.adapters.repository.mongo.model.ProductModel;
import br.com.ecommerce.core.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductModelAdapterTest {

  @Test
  public void shouldAdapterConvertToEntity() {
    ProductModelAdapter modelAdapter = new ProductModelAdapter();
    long code = 1123123123L;
    String name = "Product test";
    String description = "Product test description";
    double price = 20.23;
    String image = "url";
    int maxParcels = 10;
    int stock = 5;

    ProductModel model = new ProductModel();
    model.setCode(code);
    model.setName(name);
    model.setDescription(description);
    model.setPrice(price);
    model.setImage(image);
    model.setMaxParcels(maxParcels);
    model.setStock(stock);

    Product product = modelAdapter.toEntity(model);

    Assertions.assertEquals(code, product.getCode());
    Assertions.assertEquals(name, product.getName());
    Assertions.assertEquals(description, product.getDescription());
    Assertions.assertEquals(price, product.getPrice());
    Assertions.assertEquals(image, product.getImage());
    Assertions.assertEquals(maxParcels, product.getMaxParcels());
    Assertions.assertEquals(stock, product.getStock());
  }

  @Test
  public void shouldAdapterConvertToModel() {
    ProductModelAdapter modelAdapter = new ProductModelAdapter();
    long code = 1123123123L;
    String name = "Product test";
    String description = "Product test description";
    double price = 20.23;
    String image = "url";
    int maxParcels = 10;
    int stock = 5;

    Product product = new Product(code, name, description, price, image, maxParcels, stock);

    ProductModel model = modelAdapter.toModel(product);

    Assertions.assertEquals(code, model.getCode());
    Assertions.assertEquals(name, model.getName());
    Assertions.assertEquals(description, model.getDescription());
    Assertions.assertEquals(price, model.getPrice());
    Assertions.assertEquals(image, model.getImage());
    Assertions.assertEquals(maxParcels, model.getMaxParcels());
    Assertions.assertEquals(stock, model.getStock());
  }

}