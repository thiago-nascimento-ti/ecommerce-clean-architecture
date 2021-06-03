package br.com.ecommerce.adapters.repository.mongo.model.adapter;

import br.com.ecommerce.adapters.repository.ModelAdapter;
import br.com.ecommerce.adapters.repository.mongo.model.ItemModel;
import br.com.ecommerce.adapters.repository.mongo.model.OrderModel;
import br.com.ecommerce.core.entity.CreditCard;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import java.util.List;
import java.util.stream.Collectors;

public class OrderModelAdapter implements ModelAdapter<Order, OrderModel> {

  private final ItemModelAdapter itemModelAdapter;
  private final CreditCardModelAdapter creditCardModelAdapter;

  public OrderModelAdapter() {
    this(new ItemModelAdapter(), new CreditCardModelAdapter());
  }

  public OrderModelAdapter(ItemModelAdapter itemModelAdapter,
      CreditCardModelAdapter creditCardModelAdapter) {
    this.itemModelAdapter = itemModelAdapter;
    this.creditCardModelAdapter = creditCardModelAdapter;
  }

  @Override
  public OrderModel toModel(Order entity) {
    List<ItemModel> items = entity
        .getItems()
        .stream()
        .map(itemModelAdapter::toModel)
        .collect(Collectors.toList());

    OrderModel model = new OrderModel();
    model.setId(entity.getId());
    model.setPayable(entity.getPayable());
    model.setItemsAmount(entity.getItemsAmount());
    model.setCreditCard(creditCardModelAdapter.toModel(entity.getCreditCard()));
    model.setItems(items);
    return model;
  }

  @Override
  public Order toEntity(OrderModel model) {
    List<OrderItem> items = model
        .getItems()
        .stream()
        .map(itemModelAdapter::toEntity).collect(
            Collectors.toList());
    CreditCard creditCard = creditCardModelAdapter.toEntity(model.getCreditCard());
    return new Order(model.getId(), items, model.getItemsAmount(), model.getPayable(), creditCard);
  }
}