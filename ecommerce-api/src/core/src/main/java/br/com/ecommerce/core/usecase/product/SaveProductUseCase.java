package br.com.ecommerce.core.usecase.product;

import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.exception.DuplicatedException;
import br.com.ecommerce.core.repository.ProductRepository;

public class SaveProductUseCase {

  private final ProductRepository productRepository;

  public SaveProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void execute(Product product) {
    long productCode = product.getCode();
    if (productRepository.findByCode(productCode) != null) {
      throw new DuplicatedException("Product code " + productCode + " already exists");
    }
    this.productRepository.save(product);
  }

}