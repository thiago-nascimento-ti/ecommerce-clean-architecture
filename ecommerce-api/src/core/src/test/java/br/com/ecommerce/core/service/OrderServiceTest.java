package br.com.ecommerce.core.service;

import br.com.ecommerce.core.TestUtils;
import br.com.ecommerce.core.entity.CreditCard;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.usecase.order.CreateOrderUseCase;
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
public class OrderServiceTest {

  @Mock
  private CreateOrderUseCase saveOrderUseCase;
  @InjectMocks
  private OrderService service;

  @Test
  public void shouldSaveOrder() {
    long code = 1123123123L;
    int amount = 33;
    OrderItem item = new OrderItem(TestUtils.buildProduct(code), amount);
    CreditCard creditCard = TestUtils.buildCreditCard("1111111111111111", "2018/10", "111");
    Order order = Mockito.spy(TestUtils.buildOrder(Arrays.asList(item), creditCard));

    UUID id = UUID.randomUUID();
    Order expectedOrder = Mockito.spy(TestUtils.buildOrder(id, Arrays.asList(item), creditCard));

    Mockito.doReturn(expectedOrder).when(saveOrderUseCase)
        .execute(Mockito.eq(order), Mockito.any(UUID.class));

    Order result = service.create(order);

    Assertions.assertNotNull(result);
    Assertions.assertNotNull(result.getId());
    Assertions.assertEquals(creditCard, result.getCreditCard());
    Assertions.assertEquals(order.getItems().size(), result.getItems().size());
    Assertions.assertEquals(expectedOrder.getPayable(), result.getPayable());
    Assertions.assertEquals(expectedOrder.getItemsAmount(), result.getItemsAmount());
    Mockito.verify(saveOrderUseCase).execute(Mockito.eq(order), Mockito.any(UUID.class));
  }

}