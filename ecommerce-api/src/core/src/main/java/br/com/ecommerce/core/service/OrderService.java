package br.com.ecommerce.core.service;

import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.usecase.order.CreateOrderUseCase;
import br.com.ecommerce.core.usecase.order.FindOrderByIdUseCase;
import java.util.UUID;

public class OrderService {

  private final CreateOrderUseCase createOrderUseCase;
  private final FindOrderByIdUseCase findOrderByIdUseCase;

  public OrderService(
      CreateOrderUseCase createOrderUseCase,
      FindOrderByIdUseCase findOrderByIdUseCase
  ) {
    this.createOrderUseCase = createOrderUseCase;
    this.findOrderByIdUseCase = findOrderByIdUseCase;
  }


  public Order create(Order order) {
    return createOrderUseCase.execute(order, getSequenceId());
  }

  public Order findById(UUID id) {
    return findOrderByIdUseCase.execute(id);
  }

  public UUID getSequenceId() {
    return UUID.randomUUID();
  }

}