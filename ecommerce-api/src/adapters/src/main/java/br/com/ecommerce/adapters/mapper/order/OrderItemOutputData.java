package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.OutputMapper;
import br.com.ecommerce.adapters.mapper.product.ProductOutputData;
import br.com.ecommerce.core.entity.OrderItem;

public class OrderItemOutputData implements OutputMapper<OrderItem, OrderItemOutputData> {

  private ProductOutputData product;
  private int amount;

  @Override
  public OrderItemOutputData fromEntity(OrderItem entity) {
    amount = entity.getAmount();
    product = new ProductOutputData().fromEntity(entity.getProduct());
    return this;
  }

  public ProductOutputData getProduct() {
    return product;
  }

  public void setProduct(ProductOutputData product) {
    this.product = product;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}