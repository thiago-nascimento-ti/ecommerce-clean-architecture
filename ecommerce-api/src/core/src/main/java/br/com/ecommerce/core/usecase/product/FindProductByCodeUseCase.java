package br.com.ecommerce.core.usecase.product;

import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.exception.NotFoundException;
import br.com.ecommerce.core.repository.ProductRepository;

public class FindProductByCodeUseCase {

  private final ProductRepository repository;

  public FindProductByCodeUseCase(ProductRepository repository) {
    this.repository = repository;
  }

  public Product execute(long code) {
    Product product = this.repository.findByCode(code);
    if (product == null) {
      throw new NotFoundException("Product code " + code + " not found");
    }
    return product;
  }
}