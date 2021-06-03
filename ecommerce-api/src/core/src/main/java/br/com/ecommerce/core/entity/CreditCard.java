package br.com.ecommerce.core.entity;

import br.com.ecommerce.core.exception.CreditCardInvalidException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class CreditCard {

  private final String cardNumber;
  private final String name;
  private final String validateDate;
  private final String cvv;
  private final int parcelAmount;

  public CreditCard(String cardNumber, String name, int parcelAmount) {
    this(cardNumber, name, null, null, parcelAmount);
  }

  public CreditCard(String cardNumber, String name, String validateDate, String cvv,
      int parcelAmount) {
    this.cardNumber = cardNumber;
    this.name = name;
    this.validateDate = validateDate;
    this.cvv = cvv;
    this.parcelAmount = parcelAmount;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public String getName() {
    return name;
  }

  public String getValidateDate() {
    return validateDate;
  }

  public String getCvv() {
    return cvv;
  }

  public int getParcelAmount() {
    return parcelAmount;
  }

  public void validate() {
    if (!isCardNumberValid()) {
      throw new CreditCardInvalidException("Credit card number is not valid.");
    }
    if (!isCvvValid()) {
      throw new CreditCardInvalidException("Credit card cvv is not valid.");
    }
    if (!isValidateDateValid()) {
      throw new CreditCardInvalidException("Credit card validate date is not valid.");
    }
  }

  private boolean isValidateDateValid() {
    if (validateDate == null || validateDate.length() != 7) {
      return false;
    }
    LocalDateTime date = null;
    LocalDateTime today = getLocalDateTimeNow();
    try {
      date = getLocalDateTimeFromValidateDate();
    } catch (DateTimeParseException e) {
      return false;
    }
    boolean isYearGreaterThanNow = date.getYear() > today.getYear();
    if (isYearGreaterThanNow) {
      return true;
    }
    boolean isYearOlderThanNow = date.getYear() < today.getYear();
    if (isYearOlderThanNow) {
      return false;
    }
    boolean isMonthOlderThanNow = date.getMonthValue() < today.getMonthValue();
    if (isMonthOlderThanNow) {
      return false;
    }
    return true;
  }

  protected LocalDateTime getLocalDateTimeFromValidateDate() {
    String value = (validateDate + "-15T00:00");
    return LocalDateTime.parse(value);
  }

  protected LocalDateTime getLocalDateTimeNow() {
    return LocalDateTime.now();
  }

  private boolean isCvvValid() {
    if (cvv == null || cvv.length() != 3) {
      return false;
    }
    if (cvv.equals("123")) {
      return false;
    }
    return true;
  }

  private boolean isCardNumberValid() {
    if (cardNumber == null || cardNumber.length() != 16) {
      return false;
    }
    if (cardNumber.equals("1234123412341234")) {
      return false;
    }
    return true;
  }
}