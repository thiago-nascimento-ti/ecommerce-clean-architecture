package br.com.ecommerce.core.exception;

public class NotFoundException extends BusinessException {

  public NotFoundException(String message) {
    super(message);
  }

}