package br.com.ecommerce.infrastructure.exception;

import br.com.ecommerce.core.exception.BusinessException;
import br.com.ecommerce.core.exception.CreditCardInvalidException;
import br.com.ecommerce.core.exception.DuplicatedException;
import br.com.ecommerce.core.exception.ForbidenException;
import br.com.ecommerce.core.exception.InsufficientStockException;
import br.com.ecommerce.core.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionGlobalHandler {

  @ExceptionHandler(InsufficientStockException.class)
  public ResponseEntity handleCreditCardInvalidResource(InsufficientStockException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new InsufficientStockError(e));
  }

  @ExceptionHandler(CreditCardInvalidException.class)
  public ResponseEntity handleCreditCardInvalidResource(CreditCardInvalidException e) {
    return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED)
        .body(new MessageError(e.getMessage()));
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity handleDuplicate(BusinessException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageError(e.getMessage()));
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity handleNotFoundResource(NotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageError(e.getMessage()));
  }

  @ExceptionHandler(DuplicatedException.class)
  public ResponseEntity handleNotFoundResource(DuplicatedException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageError(e.getMessage()));
  }

  @ExceptionHandler(ForbidenException.class)
  public ResponseEntity handleNotFoundResource(ForbidenException e) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageError(e.getMessage()));
  }

}