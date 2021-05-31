package br.com.ecommerce.core.exception;

public class DuplicatedException extends BusinessException {

  public DuplicatedException(String message) {
    super(message);
  }

}
