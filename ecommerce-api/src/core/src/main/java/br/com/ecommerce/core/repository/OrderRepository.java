package br.com.ecommerce.core.repository;

import br.com.ecommerce.core.entity.Order;
import java.util.UUID;

public interface OrderRepository {

  Order findById(UUID id);

  Order create(Order order);
}