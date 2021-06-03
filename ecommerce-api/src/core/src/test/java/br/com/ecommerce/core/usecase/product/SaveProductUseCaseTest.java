package br.com.ecommerce.core.usecase.product;

import br.com.ecommerce.core.TestUtils;
import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.exception.DuplicatedException;
import br.com.ecommerce.core.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveProductUseCaseTest {

  @Mock
  private ProductRepository repository;
  @InjectMocks
  private SaveProductUseCase useCase;

  @Test
  public void shouldSaveProduct() {
    long code = 7215484564L;
    Product product = TestUtils.buildProduct(code);

    useCase.execute(product);

    Mockito.verify(repository).findByCode(code);
    Mockito.verify(repository).save(product);
  }

  @Test
  public void shouldThrowDuplicatedExceptionWhenProductCodeAlreadyExists() {
    long code = 7215484564L;
    Product product = TestUtils.buildProduct(code);

    Mockito.doReturn(product).when(repository).findByCode(code);

    Throwable exception = Assertions.assertThrows(DuplicatedException.class, () -> {
      useCase.execute(product);
    });

    Assertions.assertEquals("Product code 7215484564 already exists", exception.getMessage());
    Mockito.verify(repository).findByCode(code);
    Mockito.verify(repository, Mockito.never()).save(product);
  }

}