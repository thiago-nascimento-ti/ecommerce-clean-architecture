package br.com.ecommerce.core.service;

import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.usecase.order.SaveOrderUseCase;

public class OrderService {

  private final SaveOrderUseCase saveOrderUseCase;

  public OrderService(
      SaveOrderUseCase saveOrderUseCase
  ) {
    this.saveOrderUseCase = saveOrderUseCase;
  }

  public Order save(Order order) {
    return saveOrderUseCase.execute(order);
  }

  public Order findBy(long id) {
    return null;
  }

}