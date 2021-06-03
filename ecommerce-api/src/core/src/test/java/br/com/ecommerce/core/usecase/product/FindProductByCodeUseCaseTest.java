package br.com.ecommerce.core.usecase.product;

import br.com.ecommerce.core.TestUtils;
import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.exception.NotFoundException;
import br.com.ecommerce.core.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FindProductByCodeUseCaseTest {

  @Mock
  private ProductRepository repository;
  @InjectMocks
  private FindProductByCodeUseCase useCase;

  @Test
  public void shouldFindProductByCode() {
    long code = 7215484564L;
    Product product = TestUtils.buildProduct(code);

    Mockito.doReturn(product).when(repository).findByCode(code);

    Product result = useCase.execute(code);

    Mockito.verify(repository).findByCode(code);
    Assertions.assertEquals(code, result.getCode());
    Assertions.assertEquals(product.getName(), result.getName());
    Assertions.assertEquals(product.getDescription(), result.getDescription());
    Assertions.assertEquals(product.getPrice(), result.getPrice());
    Assertions.assertEquals(product.getImage(), result.getImage());
    Assertions.assertEquals(product.getMaxParcels(), result.getMaxParcels());
    Assertions.assertEquals(product.getStock(), result.getStock());
  }

  @Test
  public void shouldThrowNotFoundExceptionWhenProductCodeDoesNotExists() {
    long code = 7215484564L;
    Mockito.doReturn(null).when(repository).findByCode(code);

    Throwable exception = Assertions.assertThrows(NotFoundException.class, () -> {
      useCase.execute(code);
    });

    Assertions.assertEquals("Product code 7215484564 not found", exception.getMessage());
    Mockito.verify(repository).findByCode(code);
  }

}