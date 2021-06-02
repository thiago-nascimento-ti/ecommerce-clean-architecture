package br.com.ecommerce.core.usecase.order;

import br.com.ecommerce.core.TestUtils;
import br.com.ecommerce.core.entity.Product;
import br.com.ecommerce.core.exception.BusinessException;
import br.com.ecommerce.core.exception.InsufficientProductStockException;
import br.com.ecommerce.core.repository.ProductRepository;
import br.com.ecommerce.core.usecase.order.SubtractProductStockUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SubtractProductStockUseCaseTest {

  @Mock
  private ProductRepository repository;
  @InjectMocks
  private SubtractProductStockUseCase useCase;

  @Test
  public void shouldThrowBusinessExceptionWhenProductCodeDoesNotExists() {
    long code = 7215412364L;
    int stockToSubtract = 10;
    Mockito.doReturn(null).when(repository).findByCode(code);

    Throwable exception = Assertions.assertThrows(BusinessException.class, () -> {
      useCase.execute(code, stockToSubtract);
    });

    Assertions.assertEquals("It is not possible to subtract the product " + code
        + " stock because it does not exist.", exception.getMessage());
    Mockito.verify(repository).findByCode(code);
  }

  @Test
  public void shouldThrowInsufficientProductStockExceptionWhenStockIsSmallerThanToValueToSubtract() {
    long code = 7215412364L;
    int stockToSubtract = 10;
    Product product = TestUtils.buildProduct(code, 5);

    Mockito.doReturn(product).when(repository).findByCode(code);

    Throwable exception = Assertions.assertThrows(InsufficientProductStockException.class, () -> {
      useCase.execute(code, stockToSubtract);
    });

    Assertions.assertEquals("Product " + code
        + " does not have enough stock to subtract.", exception.getMessage());
    Mockito.verify(repository).findByCode(code);
  }

  @Test
  public void shouldSubtractProductStock() {
    long code = 7215412364L;
    int stockToSubtract = 10;
    Product product = TestUtils.buildProduct(code, 11);
    int expectedStock = 1;

    Mockito.doReturn(product).when(repository).findByCode(code);

    useCase.execute(code, stockToSubtract);

    Assertions.assertEquals(expectedStock, product.getStock());
    Mockito.verify(repository).findByCode(code);
    Mockito.verify(repository).save(product);
  }

}