package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.OutputMapper;
import br.com.ecommerce.core.entity.CreditCard;

public class CreditCardOutputData implements OutputMapper<CreditCard, CreditCardOutputData> {

  private String hash;
  private String name;
  private int parcelAmount;

  @Override
  public CreditCardOutputData fromEntity(CreditCard entity) {
    hash = entity.getCardNumber();
    name = entity.getName();
    parcelAmount = entity.getParcelAmount();
    return this;
  }

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