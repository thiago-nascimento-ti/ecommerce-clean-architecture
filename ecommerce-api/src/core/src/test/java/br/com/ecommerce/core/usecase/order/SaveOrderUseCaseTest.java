package br.com.ecommerce.core.usecase.order;

import br.com.ecommerce.core.TestUtils;
import br.com.ecommerce.core.entity.CreditCard;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.exception.InsufficientProductStockException;
import br.com.ecommerce.core.exception.InsufficientStockException;
import br.com.ecommerce.core.repository.OrderRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveOrderUseCaseTest {

  @Mock
  private OrderRepository repository;
  @Mock
  private SubtractProductStockUseCase subtractProductStockUseCase;
  @InjectMocks
  private SaveOrderUseCase useCase;

  @Test
  public void shouldSaveOrder() {
    long id = 10;
    long code = 1123123123L;
    int amount = 3;
    OrderItem item = new OrderItem(TestUtils.buildProduct(code), amount);
    CreditCard creditCard = TestUtils.buildCreditCard("1111111111111111", "2018/10", "111");
    Order order = Mockito.spy(TestUtils.buildOrder(Arrays.asList(item), creditCard));
    Order expectedOrder = TestUtils.buildOrder(id, Arrays.asList(item), creditCard);

    Mockito.doNothing().when(order).validate();
    Mockito.doReturn(expectedOrder).when(repository).save(order);

    Order result = useCase.execute(order);

    Mockito.verify(repository).save(order);
    Mockito.verify(order).validate();
    Mockito.verify(subtractProductStockUseCase).execute(code, 3);
    Assertions.assertEquals(expectedOrder.getId(), result.getId());
  }

  @Test
  public void shouldThrowInsufficientStockExceptionWhenSomeProductDoesNotHaveEnoughStock() {
    long id = 10;
    long code = 1123123123L;
    int amount = 3;
    OrderItem item = new OrderItem(TestUtils.buildProduct(code), amount);
    CreditCard creditCard = TestUtils.buildCreditCard("1111111111111111", "2070/10", "111");
    Order order = TestUtils.buildOrder(Arrays.asList(item), creditCard);

    Mockito.doThrow(new InsufficientProductStockException(code))
        .when(subtractProductStockUseCase)
        .execute(code, amount);

    InsufficientStockException exception = Assertions.assertThrows(InsufficientStockException.class, () -> {
      useCase.execute(order);
    });

    Assertions.assertEquals("Some products does not have enough stock to process the order.", exception.getMessage());
    Mockito.verify(repository, Mockito.never()).save(order);
    Mockito.verify(subtractProductStockUseCase).execute(code, amount);
    Assertions.assertEquals(1, exception.getProductsCodeWithoutStock().size());
    Assertions.assertEquals(code, exception.getProductsCodeWithoutStock().get(0));
  }

  @Test
  public void shouldThrowInsufficientStockExceptionWhenTwoProductDoesNotHaveEnoughStockAndReturnAllCodes() {
    long id = 10;
    long code1 = 1123123123L;
    int amount1 = 3;
    long code2 = 1123123123L;
    int amount2 = 3;

    OrderItem item1 = new OrderItem(TestUtils.buildProduct(code1), amount1);
    OrderItem item2 = new OrderItem(TestUtils.buildProduct(code2), amount2);

    CreditCard creditCard = TestUtils.buildCreditCard("1111111111111111", "2070/10", "111");
    Order order = TestUtils.buildOrder(Arrays.asList(item1, item2), creditCard);

    Mockito.doThrow(new InsufficientProductStockException(0))
        .when(subtractProductStockUseCase)
        .execute(Mockito.anyLong(), Mockito.anyInt());


    InsufficientStockException exception = Assertions.assertThrows(InsufficientStockException.class, () -> {
      useCase.execute(order);
    });

    Assertions.assertEquals("Some products does not have enough stock to process the order.", exception.getMessage());
    Mockito.verify(repository, Mockito.never()).save(order);
    Mockito.verify(subtractProductStockUseCase, Mockito.times(2)).execute(Mockito.anyLong(), Mockito.anyInt());
    Assertions.assertEquals(2, exception.getProductsCodeWithoutStock().size());
    Assertions.assertEquals(code1, exception.getProductsCodeWithoutStock().get(0));
    Assertions.assertEquals(code2, exception.getProductsCodeWithoutStock().get(1));
  }

}