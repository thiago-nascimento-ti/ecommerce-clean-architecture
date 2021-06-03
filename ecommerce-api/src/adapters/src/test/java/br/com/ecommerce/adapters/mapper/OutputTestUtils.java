package br.com.ecommerce.adapters.mapper;

import br.com.ecommerce.core.entity.CreditCard;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.entity.Product;

public class OutputTestUtils {

  public static Product buildProduct() {
    int rate = 5;
    int maxParcels = 10;
    int stock = 5;
    return buildProduct(rate, maxParcels, stock);
  }

  public static Product buildProduct(Integer rate, Integer maxParcels, Integer stock) {
    long code = 1123123123L;
    String name = "Product test";
    String description = "Product test description";
    double price = 20.23;
    String image = "url";
    return new Product(code, name, description, price, image, rate, maxParcels, stock);
  }

  public static CreditCard buildCreditCard(String cardNumber) {
    String cvv = "111";
    String name = "John Doe";
    int parcelAmount = 2;
    String validateDate = "2070/12";
    return new CreditCard(cardNumber, name, validateDate, cvv, parcelAmount);
  }

  public static OrderItem buildOrderItem() {
    int amount = 5;
    Product product = buildProduct( null, null, null);
    return new OrderItem(product, amount);
  }

}