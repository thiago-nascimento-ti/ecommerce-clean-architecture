package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.core.entity.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditCardOutputDataTest {

  @Test
  public void shouldConvertFromEntity() {
    String cvv = "111";
    String name = "John Doe";
    int parcelAmount = 2;
    String validateDate = "2070/12";
    String expectedHash = "662147282";

    CreditCard creditCard = new CreditCard(expectedHash, name, validateDate, cvv, parcelAmount);
    CreditCardOutputData outputData = new CreditCardOutputData().fromEntity(creditCard);

    Assertions.assertEquals(expectedHash, outputData.getHash());
    Assertions.assertEquals(name, outputData.getName());
    Assertions.assertEquals(parcelAmount, outputData.getParcelAmount());
  }
}