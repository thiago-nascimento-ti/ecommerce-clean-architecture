package br.com.ecommerce.adapters.repository.mongo.adapter;

import br.com.ecommerce.adapters.repository.mongo.model.OrderModel;
import br.com.ecommerce.adapters.repository.mongo.model.adapter.OrderModelAdapter;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.repository.OrderRepository;
import java.util.UUID;

public abstract class OrderRepositoryAdapter implements OrderRepository {

  private final OrderModelAdapter modelAdapter;

  public OrderRepositoryAdapter(OrderModelAdapter modelAdapter) {
    this.modelAdapter = modelAdapter;
  }

  @Override
  public Order findById(UUID id) {
    OrderModel model = findByIdBridge(id);
    if (model != null) {
      return modelAdapter.toEntity(model);
    }
    return null;
  }

  protected abstract OrderModel findByIdBridge(UUID id);

  @Override
  public Order create(Order order) {
    OrderModel model = createBridge(modelAdapter.toModel(order));
    return modelAdapter.toEntity(model);
  }

  protected abstract OrderModel createBridge(OrderModel model);

}