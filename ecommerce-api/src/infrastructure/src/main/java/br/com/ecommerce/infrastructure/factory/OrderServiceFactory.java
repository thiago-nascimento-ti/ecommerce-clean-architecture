package br.com.ecommerce.infrastructure.factory;

import br.com.ecommerce.adapters.repository.mongo.model.adapter.OrderModelAdapter;
import br.com.ecommerce.core.repository.OrderRepository;
import br.com.ecommerce.core.repository.ProductRepository;
import br.com.ecommerce.core.service.OrderService;
import br.com.ecommerce.core.usecase.order.CreateOrderUseCase;
import br.com.ecommerce.core.usecase.order.FindOrderByIdUseCase;
import br.com.ecommerce.core.usecase.order.SubtractProductStockUseCase;
import br.com.ecommerce.infrastructure.database.bridge.OrderRepositoryBridge;
import br.com.ecommerce.infrastructure.database.springdata.OrderMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceFactory {

  private final OrderMongoRepository orderRepositoryImpl;

  @Autowired
  public OrderServiceFactory(OrderMongoRepository orderRepositoryImpl) {
    this.orderRepositoryImpl = orderRepositoryImpl;
  }

  @Bean
  @Autowired
  public OrderService createOrderService(OrderRepository repository,
      ProductRepository productRepository) {
    SubtractProductStockUseCase subtractProductStockUseCase = new SubtractProductStockUseCase(
        productRepository);
    FindOrderByIdUseCase findOrderByIdUseCase = new FindOrderByIdUseCase(repository);
    CreateOrderUseCase saveOrderUseCase = new CreateOrderUseCase(repository,
        subtractProductStockUseCase, findOrderByIdUseCase);

    return new OrderService(saveOrderUseCase, findOrderByIdUseCase);
  }

  @Bean
  public OrderRepository createOrderRepository() {
    OrderModelAdapter modelAdapter = new OrderModelAdapter();
    OrderRepositoryBridge repository = new OrderRepositoryBridge(orderRepositoryImpl,
        modelAdapter);
    return repository;
  }

}