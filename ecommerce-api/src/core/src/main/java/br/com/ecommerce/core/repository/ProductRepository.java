package br.com.ecommerce.core.repository;

import br.com.ecommerce.core.entity.Page;
import br.com.ecommerce.core.entity.Product;

public interface ProductRepository {
  Page<Product> findAllPaged(int page, int itemsPerPage);
  Product findByCode(long code);
  void save(Product product);
}