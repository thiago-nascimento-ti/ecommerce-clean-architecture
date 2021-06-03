package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.InputMapper;
import br.com.ecommerce.core.entity.CreditCard;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderInputData implements InputMapper<Order> {

  private List<OrderItemInputData> items;
  private int itemsAmount;
  private double payable;
  private CreditCardInputData creditCard;

  @Override
  public Order toEntity() {
    CreditCard creditCard = this.creditCard.toEntity();
    List<OrderItem> items = this.items
        .stream()
        .map(OrderItemInputData::toEntity)
        .collect(Collectors.toList());

    return new Order(items, itemsAmount, payable, creditCard);
  }

  public List<OrderItemInputData> getItems() {
    return items;
  }

  public void setItems(List<OrderItemInputData> items) {
    this.items = items;
  }

  public int getItemsAmount() {
    return itemsAmount;
  }

  public void setItemsAmount(int itemsAmount) {
    this.itemsAmount = itemsAmount;
  }

  public double getPayable() {
    return payable;
  }

  public void setPayable(double payable) {
    this.payable = payable;
  }

  public CreditCardInputData getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCardInputData creditCard) {
    this.creditCard = creditCard;
  }
}