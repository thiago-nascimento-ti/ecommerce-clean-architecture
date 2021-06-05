import { TCart, TProduct } from "../entities";

export class SubtractProductOfCartUseCase {
  execute(cart: TCart, product: TProduct, amount: number) {
    if (cart.isProductExists(product)) {
      cart.subtract(product, amount);
    }
  }
}

