package br.com.ecommerce.core.entity;

import br.com.ecommerce.core.exception.BusinessException;
import java.util.List;
import java.util.UUID;

public class Order {
  private UUID id;
  private final List<OrderItem> items;
  private final int itemsAmount;
  private final double payable;
  private final CreditCard creditCard;

  public Order(UUID id, List<OrderItem> items, int itemsAmount, double payable, CreditCard creditCard) {
    this(items, itemsAmount, payable, creditCard);
    this.id = id;
  }

  public Order(List<OrderItem> items, int itemsAmount, double payable, CreditCard creditCard) {
    this.items = items;
    this.itemsAmount = itemsAmount;
    this.payable = payable;
    this.creditCard = creditCard;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public int getItemsAmount() {
    return itemsAmount;
  }

  public double getPayable() {
    return payable;
  }

  public CreditCard getCreditCard() {
    return creditCard;
  }

  public void validate() {
    if (!isItemsAmountValid()) {
      throw new BusinessException("The amount of items is not correct.");
    }
    if (!isPayableValueValid()) {
      throw new BusinessException("The payable value is not correct.");
    }
    creditCard.validate();
  }

  public boolean isItemsAmountValid() {
    int calculatedAmount = items
        .stream()
        .map(OrderItem::getAmount)
        .reduce(0, Integer::sum);
    return calculatedAmount == itemsAmount;
  }

  public boolean isPayableValueValid() {
    double calculatedPayable = items
        .stream()
        .mapToDouble(item -> item.getProduct().getPrice() * item.getAmount())
        .reduce(0d, Double::sum);
    return calculatedPayable == payable;
  }

}