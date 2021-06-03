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
    List<ProductModel> modelList;
    long totalItems;

    if (itemsPerPage > 0) {
      Page<ProductModel> pagedModel = findAllPagedBridge(page, itemsPerPage);
      modelList = pagedModel.getContent();
      totalItems = pagedModel.getTotalElements();
    } else {
      modelList = findAllBridge();
      totalItems = modelList.size();
    }

    return new Paged<>(convertToProductList(modelList), totalItems);
  }

  protected abstract List<ProductModel> findAllBridge();
  protected abstract Page<ProductModel> findAllPagedBridge(int page, int itemsPerPage);

  private List<Product> convertToProductList(List<ProductModel> items) {
    return items.stream()
        .map(modelAdapter::toEntity)
        .collect(Collectors.toList());
  }

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