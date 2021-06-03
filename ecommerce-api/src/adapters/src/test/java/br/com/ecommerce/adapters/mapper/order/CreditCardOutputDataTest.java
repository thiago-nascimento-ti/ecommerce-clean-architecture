package br.com.ecommerce.adapters.mapper.order;

import br.com.ecommerce.adapters.mapper.OutputTestUtils;
import br.com.ecommerce.core.entity.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditCardOutputDataTest {

  @Test
  public void shouldConvertFromEntity() {
    String expectedHash = "662147282";

    CreditCard creditCard = OutputTestUtils.buildCreditCard(expectedHash);
    CreditCardOutputData outputData = new CreditCardOutputData().fromEntity(creditCard);

    Assertions.assertEquals(expectedHash, outputData.getHash());
    Assertions.assertEquals(creditCard.getName(), outputData.getName());
    Assertions.assertEquals(creditCard.getParcelAmount(), outputData.getParcelAmount());
  }
}