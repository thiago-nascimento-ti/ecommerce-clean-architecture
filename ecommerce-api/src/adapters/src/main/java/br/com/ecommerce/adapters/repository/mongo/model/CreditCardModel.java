package br.com.ecommerce.adapters.repository.mongo.model;

public class CreditCardModel {

  private String hash;
  private String name;
  private int parcelAmount;

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getParcelAmount() {
    return parcelAmount;
  }

  public void setParcelAmount(int parcelAmount) {
    this.parcelAmount = parcelAmount;
  }

}