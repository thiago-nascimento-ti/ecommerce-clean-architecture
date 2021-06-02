package br.com.ecommerce.core.exception;

public class CreditCardInvalidException extends BusinessException {

  public CreditCardInvalidException(String message) {
    super(message);
  }
}