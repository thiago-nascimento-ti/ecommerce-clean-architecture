package br.com.ecommerce.adapters.repository.mongo.model.adapter;

import br.com.ecommerce.adapters.repository.ModelAdapter;
import br.com.ecommerce.adapters.repository.mongo.model.ItemModel;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.entity.Product;

public class ItemModelAdapter implements ModelAdapter<OrderItem, ItemModel> {

  @Override
  public ItemModel toModel(OrderItem entity) {
    ItemModel model = new ItemModel();
    model.setCode(entity.getProduct().getCode());
    model.setName(entity.getProduct().getName());
    model.setDescription(entity.getProduct().getDescription());
    model.setAmount(entity.getAmount());
    model.setPrice(entity.getProduct().getPrice());
    model.setImage(entity.getProduct().getImage());
    return model;
  }

  @Override
  public OrderItem toEntity(ItemModel model) {
    Product product = new Product(model.getCode(), model.getName(), model.getDescription(),
        model.getPrice(), model.getImage());
    return new OrderItem(product, model.getAmount());
  }
}