package br.com.ecommerce.adapters.repository.mongo.model.adapter;

import br.com.ecommerce.adapters.repository.mongo.model.CreditCardModel;
import br.com.ecommerce.core.entity.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditCardModelAdapterTest {

  @Test
  public void shouldAdapterConvertToEntity() {
    CreditCardModelAdapter modelAdapter = new CreditCardModelAdapter();
    String hash = "662147282";
    String name = "John Doe";
    int parcelAmount = 2;

    CreditCardModel model = new CreditCardModel();
    model.setHash(hash);
    model.setName(name);
    model.setParcelAmount(2);

    CreditCard creditCard = modelAdapter.toEntity(model);

    Assertions.assertEquals(name, creditCard.getName());
    Assertions.assertEquals(parcelAmount, creditCard.getParcelAmount());
    Assertions.assertEquals(hash, creditCard.getCardNumber());
    Assertions.assertNull(creditCard.getCvv());
    Assertions.assertNull(creditCard.getValidateDate());
  }

  @Test
  public void shouldAdapterConvertToModel() {
    CreditCardModelAdapter modelAdapter = new CreditCardModelAdapter();
    String numberCard = "1111111111111111";
    String cvv = "111";
    String name = "John Doe";
    int parcelAmount = 2;
    String validateDate = "2070-12";
    String expectedHash = "662147282";

    CreditCard creditCard = new CreditCard(numberCard, name, validateDate, cvv, parcelAmount);

    CreditCardModel model = modelAdapter.toModel(creditCard);

    Assertions.assertEquals(expectedHash, model.getHash());
    Assertions.assertEquals(name, model.getName());
    Assertions.assertEquals(parcelAmount, model.getParcelAmount());
  }

}