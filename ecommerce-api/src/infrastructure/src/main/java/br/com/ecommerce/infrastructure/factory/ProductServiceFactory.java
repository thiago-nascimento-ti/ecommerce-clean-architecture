package br.com.ecommerce.infrastructure.factory;

import br.com.ecommerce.adapters.repository.mongo.model.adapter.ProductModelAdapter;
import br.com.ecommerce.core.repository.ProductRepository;
import br.com.ecommerce.core.service.ProductService;
import br.com.ecommerce.core.usecase.product.FindAllProductsPagedUseCase;
import br.com.ecommerce.core.usecase.product.FindProductByCodeUseCase;
import br.com.ecommerce.core.usecase.product.SaveProductUseCase;
import br.com.ecommerce.infrastructure.database.bridge.ProductRepositoryBridge;
import br.com.ecommerce.infrastructure.database.springdata.ProductMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceFactory {

  private final ProductMongoRepository productRepositoryImpl;

  @Autowired
  public ProductServiceFactory(ProductMongoRepository productRepositoryImpl) {
    this.productRepositoryImpl = productRepositoryImpl;
  }

  @Bean
  @Autowired
  public ProductService createProductService(ProductRepository repository) {
    FindProductByCodeUseCase findProductByCodeUseCase = new FindProductByCodeUseCase(repository);
    FindAllProductsPagedUseCase findAllProductsPagedUseCase = new FindAllProductsPagedUseCase(
        repository);
    SaveProductUseCase saveProductUseCase = new SaveProductUseCase(repository);

    return new ProductService(findProductByCodeUseCase, findAllProductsPagedUseCase,
        saveProductUseCase);
  }

  @Bean
  public ProductRepository createProductRepository() {
    ProductModelAdapter modelAdapter = new ProductModelAdapter();
    ProductRepositoryBridge repository = new ProductRepositoryBridge(productRepositoryImpl,
        modelAdapter);
    return repository;
  }

}