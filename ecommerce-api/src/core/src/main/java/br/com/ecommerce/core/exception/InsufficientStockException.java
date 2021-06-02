package br.com.ecommerce.core.exception;

import java.util.List;

public class InsufficientStockException extends BusinessException {

  private final List<Long> productsCodeWithoutStock;

  public InsufficientStockException(List<Long> productsCodeWithoutStock) {
    super("Some products does not have enough stock to process the order.");
    this.productsCodeWithoutStock = productsCodeWithoutStock;
  }

  public List<Long> getProductsCodeWithoutStock() {
    return productsCodeWithoutStock;
  }

}