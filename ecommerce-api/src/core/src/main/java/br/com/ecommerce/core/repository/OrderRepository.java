package br.com.ecommerce.core.repository;

import br.com.ecommerce.core.entity.Order;

public interface OrderRepository {
  Order findById(long id);
  Order save(Order order);
}