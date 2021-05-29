import { TCreditCard, TCart, TProduct } from '../entities';
import { ICheckoutRepository, CheckoutStatus } from '../repositories';

export class ConfirmCheckoutUseCase {
  checkoutRepository: ICheckoutRepository;

  constructor(checkoutRepository: ICheckoutRepository) {
    this.checkoutRepository = checkoutRepository;
  }

  async execute(cart: TCart, creditCard: TCreditCard) {
    const checkoutResult = await this.checkoutRepository.finalize(creditCard, cart.items);
    
    if (checkoutResult.status === CheckoutStatus.InvalidCreditCard) {
      throw new InvalidCardError();
    }
    if (checkoutResult.status === CheckoutStatus.InsuficientStock) {
      throw new InsuficientStockError(checkoutResult.itemsWithInsuficientStock);
    }
  }
}

export class InvalidCardError extends Error {
  constructor() {
    super(`Credit card number is invalid`)
  }
}

export class InsuficientStockError extends Error {
  itemsWithInsuficientStock: Array<TProduct>

  constructor(itemsWithInsuficientStock: Array<TProduct>) {
    super(`${itemsWithInsuficientStock.length} products does not have enough stock`)
    this.itemsWithInsuficientStock = itemsWithInsuficientStock;
  }
}