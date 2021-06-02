package br.com.ecommerce.core.usecase.product;

import br.com.ecommerce.core.entity.Page;
import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.repository.ProductRepository;

public class FindAllProductsPagedUseCase {

  private final ProductRepository repository;

  public FindAllProductsPagedUseCase(ProductRepository repository) {
    this.repository = repository;
  }

  public Page<Product> execute(int page, int itemsPerPage) {
    return repository.findAllPaged(page, itemsPerPage);
  }

}