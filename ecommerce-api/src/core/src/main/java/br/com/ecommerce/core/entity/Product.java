package br.com.ecommerce.core.entity;

import br.com.ecommerce.core.exception.InsufficientProductStockException;

public class Product {

  private final long code;
  private final String name;
  private final String description;
  private final double price;
  private final String image;
  private final int maxParcels;
  private int stock;

  public Product(long code, String name, String description, double price, String image,
      int maxParcels, int stock) {
    this.code = code;
    this.name = name;
    this.description = description;
    this.price = price;
    this.image = image;
    this.maxParcels = maxParcels;
    this.stock = stock;
  }

  public long getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public double getPrice() {
    return price;
  }

  public String getImage() {
    return image;
  }

  public int getMaxParcels() {
    return maxParcels;
  }

  public int getStock() {
    return stock;
  }

  public void subtractStock(int stockToSubtract) {
    if (stock < stockToSubtract) {
      throw new InsufficientProductStockException(code);
    }
    stock = stock - stockToSubtract;
  }
}