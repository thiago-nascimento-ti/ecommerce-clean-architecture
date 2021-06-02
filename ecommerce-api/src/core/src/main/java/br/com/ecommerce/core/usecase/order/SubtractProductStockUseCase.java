package br.com.ecommerce.core.usecase.order;

import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.exception.BusinessException;
import br.com.ecommerce.core.repository.ProductRepository;

public class SubtractProductStockUseCase {

  private final ProductRepository productRepository;

  public SubtractProductStockUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void execute(long code, int stockToSubtract) {
    Product product = productRepository.findByCode(code);
    if (product == null) {
      throw new BusinessException("It is not possible to subtract the product " + code
          + " stock because it does not exist.");
    }
    product.subtractStock(stockToSubtract);
    productRepository.save(product);
  }

}