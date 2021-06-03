package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.InputMapper;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.entity.Product;

public class OrderItemInputData implements InputMapper<OrderItem> {

  private ProductInputData product;
  private int amount;

  @Override
  public OrderItem toEntity() {
    Product product = this.product.toEntity();
    return new OrderItem(product, amount);
  }

  public OrderItem toEntity(OrderItemInputData inputData) {
    return inputData.toEntity();
  }

  public ProductInputData getProduct() {
    return product;
  }

  public void setProduct(ProductInputData product) {
    this.product = product;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}