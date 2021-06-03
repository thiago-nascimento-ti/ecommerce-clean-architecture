package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.InputMapper;
import br.com.ecommerce.core.entity.CreditCard;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderInputData implements InputMapper<Order> {

  private List<OrderItemInputData> items;
  private CreditCardInputData creditCard;

  @Override
  public Order toEntity() {
    CreditCard creditCard = this.creditCard.toEntity();
    List<OrderItem> items = this.items
        .stream()
        .map(OrderItemInputData::toEntity)
        .collect(Collectors.toList());
    int itemsAmount = getItemsAmount(items);
    double payable = getOrderPayable(items);

    return new Order(items, itemsAmount, payable, creditCard);
  }

  private static int getItemsAmount(List<OrderItem> items) {
    return items
        .stream()
        .map(OrderItem::getAmount)
        .reduce(0, Integer::sum);
  }

  private static double getOrderPayable(List<OrderItem> items) {
    return items
        .stream()
        .map(item -> item.getProduct().getPrice() * item.getAmount())
        .reduce(0d, Double::sum);
  }

  public List<OrderItemInputData> getItems() {
    return items;
  }

  public void setItems(List<OrderItemInputData> items) {
    this.items = items;
  }

  public CreditCardInputData getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCardInputData creditCard) {
    this.creditCard = creditCard;
  }
}