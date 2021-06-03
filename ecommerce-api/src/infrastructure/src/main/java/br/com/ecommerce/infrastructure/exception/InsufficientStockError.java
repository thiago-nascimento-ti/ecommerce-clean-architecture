package br.com.ecommerce.infrastructure.exception;

import br.com.ecommerce.core.exception.InsufficientStockException;
import java.util.List;

public class InsufficientStockError extends MessageError {

  List<Long> codes;

  public InsufficientStockError(InsufficientStockException e) {
    super(e.getMessage());
    this.codes = e.getProductsCodeWithoutStock();
  }

  public List<Long> getCodes() {
    return codes;
  }

  public void setCodes(List<Long> codes) {
    this.codes = codes;
  }
}