package br.com.ecommerce.adapters.repository.mongo.model.adapter;

import br.com.ecommerce.adapters.repository.mongo.model.CreditCardModel;
import br.com.ecommerce.adapters.repository.mongo.model.ItemModel;
import br.com.ecommerce.adapters.repository.mongo.model.OrderModel;
import br.com.ecommerce.core.entity.CreditCard;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.entity.Product;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderModelAdapterTest {

  @Test
  public void shouldAdapterConvertToEntity() {
    OrderModelAdapter modelAdapter = new OrderModelAdapter();
    Long id = 10L;
    double payable = 30;
    int itemsAmount = 10;

    OrderModel model = new OrderModel();
    model.setId(id);
    model.setPayable(payable);
    model.setItemsAmount(itemsAmount);

    CreditCardModel creditCardModel = new CreditCardModel();
    creditCardModel.setHash("662147282");
    creditCardModel.setName("John Doe");
    creditCardModel.setParcelAmount(2);
    model.setCreditCard(creditCardModel);

    List<ItemModel> items = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      ItemModel item = new ItemModel();
      item.setCode(1123123123L);
      item.setName("Item test");
      item.setDescription("Item test description");
      item.setPrice(5.23);
      item.setImage("url");
      item.setAmount(5);
      items.add(item);
    }
    model.setItems(items);

    Order order = modelAdapter.toEntity(model);

    Assertions.assertEquals(id, order.getId());
    Assertions.assertEquals(payable, order.getPayable());
    Assertions.assertEquals(itemsAmount, order.getItemsAmount());
    Assertions.assertEquals(items.size(), order.getItems().size());
  }

  @Test
  public void shouldAdapterConvertToModel() {
    OrderModelAdapter modelAdapter = new OrderModelAdapter();
    List<OrderItem> items = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      Product product = new Product(1123123123L, "Item test", "Item test description", 5.23, "url", 5, 2);
      OrderItem item = new OrderItem(product, 2);
      items.add(item);
    }
    CreditCard creditCard = new CreditCard("1111111111111111", "John Doe", "2070/12", "111", 2);

    Long id = 10L;
    double payable = 30;
    int itemsAmount = 10;
    Order order = new Order(id, items, itemsAmount, payable, creditCard);

    OrderModel model = modelAdapter.toModel(order);

    Assertions.assertEquals(id, model.getId());
    Assertions.assertEquals(payable, model.getPayable());
    Assertions.assertEquals(itemsAmount, model.getItemsAmount());
    Assertions.assertNotNull(model.getCreditCard());
    Assertions.assertEquals(items.size(), model.getItems().size());
  }

}