package br.com.ecommerce.adapters.repository.mongo.model.adapter;

import br.com.ecommerce.adapters.repository.ModelAdapter;
import br.com.ecommerce.adapters.repository.mongo.model.ProductModel;
import br.com.ecommerce.core.entity.Product;

public class ProductModelAdapter implements ModelAdapter<Product, ProductModel> {

  @Override
  public ProductModel toModel(Product entity) {
    ProductModel model = new ProductModel();
    model.setCode(entity.getCode());
    model.setName(entity.getName());
    model.setDescription(entity.getDescription());
    model.setImage(entity.getImage());
    model.setPrice(entity.getPrice());
    model.setStock(entity.getStock());
    model.setMaxParcels(entity.getMaxParcels());
    return model;
  }

  @Override
  public Product toEntity(ProductModel model) {
    return new Product(model.getCode(), model.getName(), model.getDescription(), model.getPrice(),
        model.getImage(), model.getMaxParcels(), model.getStock());
  }

}