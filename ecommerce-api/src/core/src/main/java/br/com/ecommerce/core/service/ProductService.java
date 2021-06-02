package br.com.ecommerce.core.service;

import br.com.ecommerce.core.entity.Page;
import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.usecase.product.FindAllProductsPagedUseCase;
import br.com.ecommerce.core.usecase.product.FindProductByCodeUseCase;
import br.com.ecommerce.core.usecase.product.SaveProductUseCase;

public class ProductService {

  private final FindAllProductsPagedUseCase findAllProductsPagedUseCase;
  private final FindProductByCodeUseCase findProductByCodeUseCase;
  private final SaveProductUseCase saveProductUseCase;

  public ProductService(
      FindProductByCodeUseCase findProductByCodeUseCase,
      FindAllProductsPagedUseCase findAllProductsPagedUseCase,
      SaveProductUseCase saveProductUseCase
  ) {
    this.findProductByCodeUseCase = findProductByCodeUseCase;
    this.findAllProductsPagedUseCase = findAllProductsPagedUseCase;
    this.saveProductUseCase = saveProductUseCase;
  }

  public Product findProductByCode(long code) {
    return findProductByCodeUseCase.execute(code);
  }

  public int findProductStockByCode(long code) {
    return findProductByCode(code).getStock();
  }

  public Page<Product> findAllProductPaged(int page, int itemsPerPage) {
    return findAllProductsPagedUseCase.execute(page, itemsPerPage);
  }

  public void save(Product product) {
    saveProductUseCase.execute(product);
  }

}