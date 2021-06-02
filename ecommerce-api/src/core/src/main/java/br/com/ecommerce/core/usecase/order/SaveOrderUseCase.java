package br.com.ecommerce.core.usecase.order;

import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.exception.InsufficientProductStockException;
import br.com.ecommerce.core.exception.InsufficientStockException;
import br.com.ecommerce.core.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;

public class SaveOrderUseCase {

  private final OrderRepository repository;
  private final SubtractProductStockUseCase subtractProductStockUseCase;

  public SaveOrderUseCase(OrderRepository repository,
      SubtractProductStockUseCase subtractProductStockUseCase) {
    this.repository = repository;
    this.subtractProductStockUseCase = subtractProductStockUseCase;
  }

  public Order execute(Order order) {
    order.validate();

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

    return repository.save(order);
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