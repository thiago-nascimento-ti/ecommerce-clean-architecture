package br.com.ecommerce.infrastructure.database.bridge;

import br.com.ecommerce.adapters.repository.mongo.adapter.OrderRepositoryAdapter;
import br.com.ecommerce.adapters.repository.mongo.model.OrderModel;
import br.com.ecommerce.adapters.repository.mongo.model.adapter.OrderModelAdapter;
import br.com.ecommerce.infrastructure.database.springdata.OrderMongoRepository;
import java.util.UUID;

public class OrderRepositoryBridge extends OrderRepositoryAdapter {

  private final OrderMongoRepository repository;

  public OrderRepositoryBridge(OrderMongoRepository repository,
      OrderModelAdapter modelAdapter) {
    super(modelAdapter);
    this.repository = repository;
  }

  @Override
  protected OrderModel findByIdBridge(UUID id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  protected OrderModel createBridge(OrderModel model) {
    return repository.save(model);
  }

}