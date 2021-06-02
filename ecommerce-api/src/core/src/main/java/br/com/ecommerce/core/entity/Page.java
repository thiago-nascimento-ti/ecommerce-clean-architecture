package br.com.ecommerce.core.entity;

import java.util.List;

public class Page<T> {

  private final List<T> items;
  private int totalItems;

  public Page(List<T> items, int totalItems) {
    this.items = items;
    this.totalItems = totalItems;
  }

  public List<T> getItems() {
    return items;
  }

  public int getTotalItems() {
    return totalItems;
  }
}