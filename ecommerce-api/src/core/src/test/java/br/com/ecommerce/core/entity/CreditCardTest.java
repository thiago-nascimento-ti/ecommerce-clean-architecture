package br.com.ecommerce.core.entity;

import br.com.ecommerce.core.TestUtils;
import br.com.ecommerce.core.exception.CreditCardInvalidException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreditCardTest {

  @Test
  public void shouldBeValid() {
    String validateDate = "2070/01";
    String cvv = "111";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    creditCard.validate();
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenCardNumberIsLessThan16Digits() {
    String validateDate = "2070/01";
    String cvv = "11";
    String cardNumber = "111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card number is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenCardNumberIsGreaterThan16Digits() {
    String validateDate = "2070/01";
    String cvv = "1111";
    String cardNumber = "11111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card number is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenCardNumberIsNull() {
    String validateDate = "2070/01";
    String cvv = "111";
    String cardNumber = null;
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card number is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenCardNumberIs1234123412341234() {
    String validateDate = "2070/01";
    String cvv = "111";
    String cardNumber = "1234123412341234";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card number is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenCvvIsLessThan3Digits() {
    String validateDate = "2070/01";
    String cvv = "11";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card cvv is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenCvvIsGreaterThan3Digits() {
    String validateDate = "2070/01";
    String cvv = "1111";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card cvv is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenCvvIsNull() {
    String validateDate = "2070/01";
    String cvv = null;
    String cardNumber = "1111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card cvv is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenCvvIs123() {
    String validateDate = "2070/01";
    String cvv = "123";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card cvv is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenValidateDateIsNull() {
    String validateDate = null;
    String cvv = "111";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card validate date is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenValidateDateIsLessThan7Digits() {
    String validateDate = "2020/1";
    String cvv = "111";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card validate date is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenValidateDateIsGreaterThan7Digits() {
    String validateDate = "2020/001";
    String cvv = "111";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card validate date is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenValidateDateCouldNotBeParsed() {
    String validateDate = "2020/asd";
    String cvv = "111";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv);

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card validate date is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenYearIsBeforeThanNow() {
    String validateDate = "2020/05";
    String cvv = "111";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = Mockito.spy(TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv));
    LocalDateTime today = LocalDateTime.parse("2021-05-01T00:00");
    Mockito.doReturn(today).when(creditCard).getLocalDateTimeNow();

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card validate date is not valid.", exception.getMessage());
  }

  @Test
  public void shouldThrowCreditCardInvalidExceptionWhenYearIsEqualsButBeforeThanNow() {
    String validateDate = "2021/04";
    String cvv = "111";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = Mockito.spy(TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv));
    LocalDateTime today = LocalDateTime.parse("2021-05-01T00:00");
    Mockito.doReturn(today).when(creditCard).getLocalDateTimeNow();

    Throwable exception = Assertions.assertThrows(CreditCardInvalidException.class, () -> {
      creditCard.validate();
    });

    Assertions.assertEquals("Credit card validate date is not valid.", exception.getMessage());
  }

  @Test
  public void shouldBeValidWhenYearIsGreaterThanNow() {
    String validateDate = "2022/01";
    String cvv = "111";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = Mockito.spy(TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv));
    LocalDateTime today = LocalDateTime.parse("2021-05-01T00:00");
    Mockito.doReturn(today).when(creditCard).getLocalDateTimeNow();

    creditCard.validate();
  }

  @Test
  public void shouldBeValidWhenYearAndMonthIsEquals() {
    String validateDate = "2021/05";
    String cvv = "111";
    String cardNumber = "1111111111111111";
    CreditCard creditCard = Mockito.spy(TestUtils
        .buildCreditCard(cardNumber, validateDate, cvv));
    LocalDateTime today = LocalDateTime.parse("2021-05-01T00:00");
    Mockito.doReturn(today).when(creditCard).getLocalDateTimeNow();

    creditCard.validate();
  }

}