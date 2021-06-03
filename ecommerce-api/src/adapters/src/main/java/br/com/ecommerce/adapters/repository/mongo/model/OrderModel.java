package br.com.ecommerce.adapters.repository.mongo.model;

import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("orders")
public class OrderModel {

  @Id
  private UUID id;
  private List<ItemModel> items;
  private int itemsAmount;
  private double payable;
  private CreditCardModel creditCard;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<ItemModel> getItems() {
    return items;
  }

  public void setItems(List<ItemModel> items) {
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

  public CreditCardModel getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCardModel creditCard) {
    this.creditCard = creditCard;
  }

}