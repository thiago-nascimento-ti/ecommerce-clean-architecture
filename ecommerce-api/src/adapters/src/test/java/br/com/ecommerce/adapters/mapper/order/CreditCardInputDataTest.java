package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.core.entity.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditCardInputDataTest {

  @Test
  public void shouldConvertToEntity() {
    String cardNumber = "1111111111111111";
    String cvv = "111";
    String name = "John Doe";
    int parcelAmount = 2;
    String validateDate = "2070/12";

    CreditCardInputData inputData = new CreditCardInputData();
    inputData.setCardNumber(cardNumber);
    inputData.setCvv(cvv);
    inputData.setName(name);
    inputData.setValidateDate(validateDate);
    inputData.setParcelAmount(parcelAmount);

    CreditCard creditCard = inputData.toEntity();

    Assertions.assertEquals(cardNumber, creditCard.getCardNumber());
    Assertions.assertEquals(cvv, creditCard.getCvv());
    Assertions.assertEquals(name, creditCard.getName());
    Assertions.assertEquals(parcelAmount, creditCard.getParcelAmount());
    Assertions.assertEquals(validateDate, creditCard.getValidateDate());
  }
}