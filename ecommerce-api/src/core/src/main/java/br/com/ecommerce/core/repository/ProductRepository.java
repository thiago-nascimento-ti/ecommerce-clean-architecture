package br.com.ecommerce.core.repository;

import br.com.ecommerce.core.entity.Paged;
import br.com.ecommerce.core.entity.Product;

public interface ProductRepository {
  Paged<Product> findAllPaged(int page, int itemsPerPage);
  Product findByCode(long code);
  void save(Product product);
}