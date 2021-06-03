package br.com.ecommerce.adapters.repository.mongo.adapter;

import br.com.ecommerce.adapters.repository.mongo.model.OrderModel;
import br.com.ecommerce.adapters.repository.mongo.model.adapter.OrderModelAdapter;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.repository.OrderRepository;

public abstract class OrderRepositoryAdapter implements OrderRepository {

  private final OrderModelAdapter modelAdapter;

  public OrderRepositoryAdapter(OrderModelAdapter modelAdapter) {
    this.modelAdapter = modelAdapter;
  }

  @Override
  public Order findById(Long id) {
    OrderModel model = findByIdBridge(id);
    return modelAdapter.toEntity(model);
  }

  protected abstract OrderModel findByIdBridge(Long id);

  @Override
  public Order save(Order order) {
    OrderModel model = saveBridge(modelAdapter.toModel(order));
    return modelAdapter.toEntity(model);
  }

  protected abstract OrderModel saveBridge(OrderModel model);

}