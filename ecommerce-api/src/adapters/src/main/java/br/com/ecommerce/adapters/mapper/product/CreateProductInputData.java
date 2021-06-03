package br.com.ecommerce.adapters.mapper.product;

import br.com.ecommerce.adapters.mapper.InputMapper;
import br.com.ecommerce.core.entity.Product;

public class CreateProductInputData implements InputMapper<Product> {

  private long id;
  private String name;
  private String description;
  private double price;
  private String image;
  private Integer maxParcels;
  private Integer stock;

  @Override
  public Product toEntity() {
    return new Product(id, name, description, price, image, maxParcels, stock);
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