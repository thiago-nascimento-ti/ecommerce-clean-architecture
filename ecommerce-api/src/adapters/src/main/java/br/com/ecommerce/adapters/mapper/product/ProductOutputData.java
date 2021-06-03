package br.com.ecommerce.adapters.mapper.product;

import br.com.ecommerce.adapters.mapper.OutputMapper;
import br.com.ecommerce.core.entity.Product;

public class ProductOutputData implements OutputMapper<Product, ProductOutputData> {

  private long id;
  private String name;
  private String description;
  private double price;
  private String image;
  private Integer maxParcels;
  private Integer stock;

  @Override
  public ProductOutputData fromEntity(Product entity) {
    id = entity.getCode();
    name = entity.getName();
    description = entity.getDescription();
    price = entity.getPrice();
    image = entity.getImage();
    maxParcels = entity.getMaxParcels();
    stock = entity.getStock();
    return this;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Integer getMaxParcels() {
    return maxParcels;
  }

  public void setMaxParcels(Integer maxParcels) {
    this.maxParcels = maxParcels;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }
}