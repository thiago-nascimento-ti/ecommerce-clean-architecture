package br.com.ecommerce.core.service;

import br.com.ecommerce.core.TestUtils;
import br.com.ecommerce.core.entity.Paged;
import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.usecase.product.FindAllProductsPagedUseCase;
import br.com.ecommerce.core.usecase.product.FindProductByCodeUseCase;
import br.com.ecommerce.core.usecase.product.SaveProductUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @Mock
  private FindProductByCodeUseCase findProductByCodeUseCase;
  @Mock
  private FindAllProductsPagedUseCase findAllProductsPagedUseCase;
  @Mock
  private SaveProductUseCase saveProductUseCase;
  @InjectMocks
  private ProductService service;

  @Test
  public void shouldFindProductByCode() {
    long code = 7215484564L;
    Product product = TestUtils.buildProduct(code);

    Mockito.doReturn(product).when(findProductByCodeUseCase).execute(code);

    Product result = service.findProductByCode(code);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(code, result.getCode());
    Assertions.assertEquals(product.getName(), result.getName());
    Assertions.assertEquals(product.getDescription(), result.getDescription());
    Assertions.assertEquals(product.getPrice(), result.getPrice());
    Assertions.assertEquals(product.getImage(), result.getImage());
    Assertions.assertEquals(product.getMaxParcels(), result.getMaxParcels());
    Assertions.assertEquals(product.getStock(), result.getStock());
    Mockito.verify(findProductByCodeUseCase).execute(code);
  }

  @Test
  public void shouldReturnProductStockByCode() {
    long code = 7215484564L;
    Product product = TestUtils.buildProduct(code, 10);
    int expetecStock = 10;

    Mockito.doReturn(product).when(findProductByCodeUseCase).execute(code);

    int stock = service.findProductStockByCode(code);

    Assertions.assertEquals(expetecStock, stock);
    Mockito.verify(findProductByCodeUseCase).execute(code);
  }

  @Test
  public void shouldFindPagedProducts() {
    int page = 1;
    int itemsPerPage = 10;
    Paged<Product> paged = new Paged<>(
        TestUtils.buildProductList(7823159138L, 3163634337L, 7528877106L),
        3);
    Mockito.doReturn(paged).when(findAllProductsPagedUseCase).execute(page, itemsPerPage);

    Paged<Product> pagedResult = service.findAllProductPaged(page, itemsPerPage);

    Assertions.assertEquals(pagedResult, paged);
    Mockito.verify(findAllProductsPagedUseCase).execute(page, itemsPerPage);
  }

  @Test
  public void shouldSaveProduct() {
    long code = 7215484564L;
    Product product = TestUtils.buildProduct(code);

    service.save(product);

    Mockito.verify(saveProductUseCase).execute(product);
  }
}