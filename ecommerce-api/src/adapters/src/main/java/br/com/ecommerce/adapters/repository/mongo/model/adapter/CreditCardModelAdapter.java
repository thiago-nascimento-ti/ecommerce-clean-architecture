package br.com.ecommerce.adapters.repository.mongo.model.adapter;

import br.com.ecommerce.adapters.repository.ModelAdapter;
import br.com.ecommerce.adapters.repository.mongo.model.CreditCardModel;
import br.com.ecommerce.core.entity.CreditCard;
import java.util.Objects;

public class CreditCardModelAdapter implements ModelAdapter<CreditCard, CreditCardModel> {

  @Override
  public CreditCardModel toModel(CreditCard entity) {
    CreditCardModel model = new CreditCardModel();
    int hash = Objects.hash(entity.getCardNumber(), entity.getCvv());
    model.setHash(String.valueOf(hash));
    model.setName(entity.getName());
    model.setParcelAmount(entity.getParcelAmount());
    return model;
  }

  @Override
  public CreditCard toEntity(CreditCardModel model) {
    return new CreditCard(model.getHash(), model.getName(), model.getParcelAmount());
  }
}