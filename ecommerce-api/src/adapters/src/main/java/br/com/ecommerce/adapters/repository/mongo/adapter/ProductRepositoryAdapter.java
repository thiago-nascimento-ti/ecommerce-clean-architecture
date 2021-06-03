package br.com.ecommerce.adapters.repository.mongo.adapter;

import br.com.ecommerce.adapters.repository.mongo.model.ProductModel;
import br.com.ecommerce.adapters.repository.mongo.model.adapter.ProductModelAdapter;
import br.com.ecommerce.core.entity.Paged;
import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public abstract class ProductRepositoryAdapter implements ProductRepository {

  private final ProductModelAdapter modelAdapter;

  public ProductRepositoryAdapter(ProductModelAdapter modelAdapter) {
    this.modelAdapter = modelAdapter;
  }

  @Override
  public Paged<Product> findAllPaged(int page, int itemsPerPage) {
    Page<ProductModel> pagedModel = findAllPagedBridge(page, itemsPerPage);

    List<Product> products = pagedModel
        .getContent()
        .stream()
        .map(modelAdapter::toEntity)
        .collect(Collectors.toList());

    return new Paged<>(products, pagedModel.getTotalElements());
  }

  protected abstract Page<ProductModel> findAllPagedBridge(int page, int itemsPerPage);

  @Override
  public Product findByCode(long code) {
    ProductModel model = findByCodeBridge(code);
    if (model == null) {
      return null;
    }
    return modelAdapter.toEntity(model);
  }

  protected abstract ProductModel findByCodeBridge(long code);

  @Override
  public void save(Product product) {
    saveBridge(modelAdapter.toModel(product));
  }

  protected abstract void saveBridge(ProductModel productModel);

}