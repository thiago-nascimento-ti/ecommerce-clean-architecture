package br.com.ecommerce.core.usecase.order;

import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.exception.DuplicatedException;
import br.com.ecommerce.core.exception.InsufficientProductStockException;
import br.com.ecommerce.core.exception.InsufficientStockException;
import br.com.ecommerce.core.exception.NotFoundException;
import br.com.ecommerce.core.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateOrderUseCase {

  private final OrderRepository repository;
  private final SubtractProductStockUseCase subtractProductStockUseCase;
  private final FindOrderByIdUseCase findOrderByIdUseCase;

  public CreateOrderUseCase(OrderRepository repository,
      SubtractProductStockUseCase subtractProductStockUseCase,
      FindOrderByIdUseCase findOrderByIdUseCase) {
    this.repository = repository;
    this.subtractProductStockUseCase = subtractProductStockUseCase;
    this.findOrderByIdUseCase = findOrderByIdUseCase;
  }

  public Order execute(Order order, UUID id) {
    order.validate();
    try {
      findOrderByIdUseCase.execute(id);
      throw new DuplicatedException("Order id already exists");
    } catch (NotFoundException e) {
      order.setId(id);
      List<Long> productsCodeWithoutStock = order
          .getItems()
          .stream()
          .filter(item -> !subtractProductStock(item))
          .mapToLong(this::getProductCode)
          .boxed()
          .collect(Collectors.toList());

      if (!productsCodeWithoutStock.isEmpty()) {
        throw new InsufficientStockException(productsCodeWithoutStock);
      }
      return repository.create(order);
    }
  }

  private Long getProductCode(OrderItem item) {
    return item.getProduct().getCode();
  }

  private boolean subtractProductStock(OrderItem item) {
    long code = item.getProduct().getCode();
    int stockToSubtract = item.getAmount();
    try {
      subtractProductStockUseCase.execute(code, stockToSubtract);
    } catch (InsufficientProductStockException e) {
      return false;
    }
    return true;
  }

}