package br.com.ecommerce.core.entity;

import br.com.ecommerce.core.TestUtils;
import br.com.ecommerce.core.exception.BusinessException;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderTest {

  @Test
  public void shouldBeValid() {
    long code = 1123123123L;
    int amount = 3;
    double payable = 20.20 * 3;
    OrderItem item = new OrderItem(TestUtils.buildProduct(code), amount);
    CreditCard creditCard = TestUtils.buildCreditCard("1111111111111111", "2070/10", "111");
    Order order = new Order(Arrays.asList(item), amount, payable, creditCard);

    order.validate();
  }

  @Test
  public void shouldThrowBusinessExceptionWhenAmountOfItemsIsNotRight() {
    long code = 1123123123L;
    int amount = 3;
    double payable = 20.20 * 3;
    OrderItem item = new OrderItem(TestUtils.buildProduct(code), amount);
    CreditCard creditCard = TestUtils.buildCreditCard("1111111111111111", "2070/10", "111");
    Order order = new Order(Arrays.asList(item), 5, payable, creditCard);

    Throwable exception = Assertions.assertThrows(BusinessException.class, () -> {
      order.validate();
    });

    Assertions.assertEquals("The amount of items is not correct.", exception.getMessage());
  }

  @Test
  public void shouldThrowBusinessExceptionWhenPayableValueIsNotRight() {
    long code = 1123123123L;
    int amount = 3;
    OrderItem item = new OrderItem(TestUtils.buildProduct(code), amount);
    CreditCard creditCard = TestUtils.buildCreditCard("1111111111111111", "2070/10", "111");
    Order order = new Order(Arrays.asList(item), amount, 50, creditCard);

    Throwable exception = Assertions.assertThrows(BusinessException.class, () -> {
      order.validate();
    });

    Assertions.assertEquals("The payable value is not correct.", exception.getMessage());
  }
}