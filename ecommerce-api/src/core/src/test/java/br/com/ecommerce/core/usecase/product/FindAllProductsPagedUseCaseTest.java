package br.com.ecommerce.core.usecase.product;

import br.com.ecommerce.core.TestUtils;
import br.com.ecommerce.core.entity.Page;
import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.repository.ProductRepository;
import br.com.ecommerce.core.usecase.product.FindAllProductsPagedUseCase;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FindAllProductsPagedUseCaseTest {

  @Mock
  private ProductRepository repository;
  @InjectMocks
  private FindAllProductsPagedUseCase useCase;

  @Test
  public void shouldReturnPagedProducts() {
    int page = 1;
    int itemsPerPage = 10;
    List<Product> products = TestUtils.buildProductList(7823159138L, 3163634337L, 7528877106L);
    Page<Product> paged = new Page<>(products, products.size());
    Mockito.doReturn(paged).when(repository).findAllPaged(page, itemsPerPage);
    Long[] expectedCodes = {7823159138L, 3163634337L, 7528877106L};

    Page<Product> pagedResult =useCase.execute(page, itemsPerPage);

    Assertions.assertEquals(3, pagedResult.getTotalItems());
    Mockito.verify(repository).findAllPaged(page, itemsPerPage);
  }

}