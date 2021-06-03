package br.com.ecommerce.core;

import br.com.ecommerce.core.entity.CreditCard;
import br.com.ecommerce.core.entity.Order;
import br.com.ecommerce.core.entity.OrderItem;
import br.com.ecommerce.core.entity.Product;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TestUtils {

  public static Product buildProduct(long code) {
    int stock = 5;
    return buildProduct(code, stock);
  }

  public static Product buildProduct(long code, int stock) {
    String name = "Product Test";
    String description = "Product description";
    double price = 20.20;
    String image = "url";
    int maxParcels = 10;
    int rate = 5;
    return new Product(code, name, description, price, image, rate, maxParcels, stock);
  }

  public static CreditCard buildCreditCard(String cardNumber, String validateDate, String cvv) {
    String name = "TESTE";
    int parcelAmount = 1;
    return new CreditCard(cardNumber, name, validateDate, cvv, parcelAmount);
  }

  public static Order buildOrder(UUID id, List<OrderItem> items, CreditCard creditCard) {
    int itemsAmount = getItemsAmount(items);
    double payable = getOrderPayable(items);
    return new Order(id, items, itemsAmount, payable, creditCard);
  }

  public static Order buildOrder(List<OrderItem> items, CreditCard creditCard) {
    int itemsAmount = getItemsAmount(items);
    double payable = getOrderPayable(items);
    return new Order(items, itemsAmount, payable, creditCard);
  }

  private static int getItemsAmount(List<OrderItem> items) {
    return items
        .stream()
        .map(OrderItem::getAmount)
        .reduce(0, Integer::sum);
  }

  private static double getOrderPayable(List<OrderItem> items) {
    return items
        .stream()
        .map(item -> item.getProduct().getPrice() * item.getAmount())
        .reduce(0d, Double::sum);
  }

  public static List<Product> buildProductList(long ...codes) {
    return Arrays.stream(codes).mapToObj(
        code -> buildProduct(code))
        .collect(Collectors.toList());
  }

}