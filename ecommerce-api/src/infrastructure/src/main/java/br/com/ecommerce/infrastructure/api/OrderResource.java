package br.com.ecommerce.infrastructure.api;

import br.com.ecommerce.adapters.mapper.order.CreateOrderInputData;
import br.com.ecommerce.adapters.mapper.order.OrderOutputData;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.service.OrderService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
public class OrderResource extends RestResource {

  private final OrderService service;

  @Autowired
  public OrderResource(OrderService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> create(@RequestBody CreateOrderInputData createOrderInputData) {
    Order order = service.create(createOrderInputData.toEntity());
    return created(getLocation(order.getId()));
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public OrderOutputData findById(@PathVariable("id") UUID id) {
    Order order = service.findById(id);
    return new OrderOutputData().fromEntity(order);
  }

}