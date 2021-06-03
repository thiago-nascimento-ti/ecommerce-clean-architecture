package br.com.ecommerce.infrastructure.database.bridge;

import br.com.ecommerce.adapters.repository.mongo.adapter.ProductRepositoryAdapter;
import br.com.ecommerce.adapters.repository.mongo.model.ProductModel;
import br.com.ecommerce.adapters.repository.mongo.model.adapter.ProductModelAdapter;
import br.com.ecommerce.infrastructure.database.springdata.ProductMongoRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class ProductRepositoryBridge extends ProductRepositoryAdapter {

  private final ProductMongoRepository repository;

  public ProductRepositoryBridge(ProductMongoRepository repository,
      ProductModelAdapter modelAdapter) {
    super(modelAdapter);
    this.repository = repository;
  }

  @Override
  protected List<ProductModel> findAllBridge() {
    return repository.findAll();
  }

  @Override
  protected Page<ProductModel> findAllPagedBridge(int page, int itemsPerPage) {
    return repository.findAll(PageRequest.of(page - 1, itemsPerPage));
  }

  @Override
  protected ProductModel findByCodeBridge(long code) {
    return repository.findById(code).orElse(null);
  }

  @Override
  protected void saveBridge(ProductModel productModel) {
    repository.save(productModel);
  }

}