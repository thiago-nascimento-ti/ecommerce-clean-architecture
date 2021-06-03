package br.com.ecommerce.core.entity;

import java.util.List;

public class Paged<T> {

  private final List<T> items;
  private long totalItems;

  public Paged(List<T> items, long totalItems) {
    this.items = items;
    this.totalItems = totalItems;
  }

  public List<T> getItems() {
    return items;
  }

  public long getTotalItems() {
    return totalItems;
  }
}