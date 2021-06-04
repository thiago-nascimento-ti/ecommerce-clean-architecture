package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.core.entity.CreditCard;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderOutputDataTest {

  @Test
  public void shouldConvertFromEntity() {
    List<OrderItem> items = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      Product product = new Product(1123123123L, "Item test", "Item test description", 5.23, "url",
          3D, 5, 2);
      OrderItem item = new OrderItem(product, 2);
      items.add(item);
    }
    String expectedHash = "662147282";
    String name = "John Doe";
    int parcelsAmount = 2;
    CreditCard creditCard = new CreditCard(expectedHash, name, "2070/12", "111", parcelsAmount);

    UUID id = UUID.randomUUID();
    double payable = 30;
    int itemsAmount = 10;
    Order order = new Order(id, items, itemsAmount, payable, creditCard);

    OrderOutputData outputData = new OrderOutputData().fromEntity(order);

    Assertions.assertEquals(id, outputData.getId());
    Assertions.assertEquals(payable, outputData.getPayable());
    Assertions.assertEquals(itemsAmount, outputData.getItemsAmount());
    Assertions.assertEquals(expectedHash, outputData.getCreditCard().getHash());
    Assertions.assertEquals(name, outputData.getCreditCard().getName());
    Assertions.assertEquals(parcelsAmount, outputData.getCreditCard().getParcelAmount());
    Assertions.assertEquals(items.size(), outputData.getItems().size());
  }

}