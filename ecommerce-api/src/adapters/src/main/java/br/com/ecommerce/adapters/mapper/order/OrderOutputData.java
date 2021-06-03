package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.OutputMapper;
import br.com.ecommerce.core.entity.Order;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderOutputData implements OutputMapper<Order, OrderOutputData> {

  private UUID id;
  private List<OrderItemOutputData> items;
  private int itemsAmount;
  private double payable;
  private CreditCardOutputData creditCard;

  @Override
  public OrderOutputData fromEntity(Order entity) {
    id = entity.getId();
    itemsAmount = entity.getItemsAmount();
    payable = entity.getPayable();
    creditCard = new CreditCardOutputData()
        .fromEntity(entity.getCreditCard());
    items = entity
        .getItems()
        .stream()
        .map(item -> new OrderItemOutputData().fromEntity(item))
        .collect(Collectors.toList());
    return this;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<OrderItemOutputData> getItems() {
    return items;
  }

  public void setItems(List<OrderItemOutputData> items) {
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

  public CreditCardOutputData getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCardOutputData creditCard) {
    this.creditCard = creditCard;
  }
}