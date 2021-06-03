package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.InputDataTestUtils;
import br.com.ecommerce.core.entity.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditCardInputDataTest {

  @Test
  public void shouldConvertToEntity() {
    CreditCardInputData inputData = InputDataTestUtils.buildCreditCardInputData();

    CreditCard creditCard = inputData.toEntity();

    Assertions.assertEquals(inputData.getCardNumber(), creditCard.getCardNumber());
    Assertions.assertEquals(inputData.getCvv(), creditCard.getCvv());
    Assertions.assertEquals(inputData.getName(), creditCard.getName());
    Assertions.assertEquals(inputData.getParcelAmount(), creditCard.getParcelAmount());
    Assertions.assertEquals(inputData.getValidateDate(), creditCard.getValidateDate());
  }
}