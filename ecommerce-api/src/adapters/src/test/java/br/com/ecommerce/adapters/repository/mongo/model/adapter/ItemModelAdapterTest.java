package br.com.ecommerce.adapters.repository.mongo.model.adapter;

import br.com.ecommerce.adapters.repository.mongo.model.ItemModel;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemModelAdapterTest {

  @Test
  public void shouldAdapterConvertToEntity() {
    ItemModelAdapter modelAdapter = new ItemModelAdapter();
    long code = 1123123123L;
    String name = "Item test";
    String description = "Item test description";
    double price = 5.23;
    String image = "url";
    int amount = 5;

    ItemModel model = new ItemModel();
    model.setCode(code);
    model.setName(name);
    model.setDescription(description);
    model.setPrice(price);
    model.setImage(image);
    model.setAmount(amount);

    OrderItem item = modelAdapter.toEntity(model);

    Assertions.assertEquals(amount, item.getAmount());
    Assertions.assertEquals(code, item.getProduct().getCode());
    Assertions.assertEquals(name, item.getProduct().getName());
    Assertions.assertEquals(description, item.getProduct().getDescription());
    Assertions.assertEquals(price, item.getProduct().getPrice());
    Assertions.assertEquals(image, item.getProduct().getImage());
    Assertions.assertNull(item.getProduct().getMaxParcels());
    Assertions.assertNull(item.getProduct().getStock());
  }

  @Test
  public void shouldAdapterConvertToModel() {
    ItemModelAdapter modelAdapter = new ItemModelAdapter();
    long code = 1123123123L;
    String name = "Product test";
    String description = "Product test description";
    double price = 20.23;
    String image = "url";
    int maxParcels = 10;
    int stock = 10;
    int amount = 5;

    Product product = new Product(code, name, description, price, image, maxParcels, stock);
    OrderItem item = new OrderItem(product, amount);

    ItemModel model = modelAdapter.toModel(item);

    Assertions.assertEquals(amount, model.getAmount());
    Assertions.assertEquals(code, model.getCode());
    Assertions.assertEquals(name, model.getName());
    Assertions.assertEquals(description, model.getDescription());
    Assertions.assertEquals(price, model.getPrice());
    Assertions.assertEquals(image, model.getImage());
  }

}