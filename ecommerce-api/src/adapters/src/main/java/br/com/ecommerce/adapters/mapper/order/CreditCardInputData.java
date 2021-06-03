package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.InputMapper;
import br.com.ecommerce.core.entity.CreditCard;

public class CreditCardInputData implements InputMapper<CreditCard> {

  private String cardNumber;
  private String name;
  private String validateDate;
  private String cvv;
  private int parcelAmount;

  @Override
  public CreditCard toEntity() {
    return new CreditCard(cardNumber, name, validateDate, cvv, parcelAmount);
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValidateDate() {
    return validateDate;
  }

  public void setValidateDate(String validateDate) {
    this.validateDate = validateDate;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public int getParcelAmount() {
    return parcelAmount;
  }

  public void setParcelAmount(int parcelAmount) {
    this.parcelAmount = parcelAmount;
  }

}