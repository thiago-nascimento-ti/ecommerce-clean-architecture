package br.com.ecommerce.core.usecase.order;

import br.com.ecommerce.core.TestUtils;
import br.com.ecommerce.core.entity.CreditCard;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.exception.NotFoundException;
import br.com.ecommerce.core.repository.OrderRepository;
import java.util.Arrays;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FindOrderByIdUseCaseTest {

  @Mock
  private OrderRepository repository;
  @InjectMocks
  private FindOrderByIdUseCase useCase;

  @Test
  public void shouldFindOrderById() {
    UUID id = UUID.randomUUID();
    long code = 1123123123L;
    OrderItem item = new OrderItem(TestUtils.buildProduct(code), 1);
    CreditCard creditCard = TestUtils.buildCreditCard("1234123412341234", "2018/10", "123");
    Order order = TestUtils.buildOrder(id, Arrays.asList(item), creditCard);

    Mockito.doReturn(order).when(repository).findById(id);

    Order result = useCase.execute(id);

    Mockito.verify(repository).findById(id);
    Assertions.assertEquals(id, result.getId());
    Assertions.assertEquals(result, order);
  }

  @Test
  public void shouldThrowNotFoundExceptionWhenOrderIdDoesNotExists() {
    UUID id = UUID.randomUUID();
    Mockito.doReturn(null).when(repository).findById(id);

    Throwable exception = Assertions.assertThrows(NotFoundException.class, () -> {
      useCase.execute(id);
    });

    Assertions.assertEquals("Order not found", exception.getMessage());
    Mockito.verify(repository).findById(id);
  }

}