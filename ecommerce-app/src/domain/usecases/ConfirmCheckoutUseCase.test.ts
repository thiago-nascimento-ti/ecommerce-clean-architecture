import { TCart, TProduct, TCreditCard } from '../entities';
import { ConfirmCheckoutUseCase, InvalidCardError, InsuficientStockError} from './ConfirmCheckoutUseCase';
import { FakeCheckoutRepository } from '../repositories/CheckoutRepository.util';
import { CheckoutStatus } from '../repositories';

describe('add product on cart use case', () => {
  const checkoutRepository: FakeCheckoutRepository = new FakeCheckoutRepository();
  const confirmCheckoutUseCase: ConfirmCheckoutUseCase = new ConfirmCheckoutUseCase(checkoutRepository);
  const product: TProduct = {id: 1, name: "product 01", description: "", price: 10, image: "", rate: 2, maxParcelas: 1};

  it('should successful confirm checkout', async () => {
    checkoutRepository.checkoutResult.status = CheckoutStatus.Successful;
    const cart: TCart = new TCart();
    cart.add(product, 5);
    const creditCard: TCreditCard = {
      number: "5274818933667191",
      cvv: "456",
      validateDate: "28/07/2020"
    }

    await confirmCheckoutUseCase.execute(cart, creditCard);
  });

  it('should throw InvalidCreditCardError when CheckoutStatus is InvalidCreditCard', async () => {
    checkoutRepository.checkoutResult.status = CheckoutStatus.InvalidCreditCard;
    const cart: TCart = new TCart();
    cart.add(product, 5);
    const creditCard: TCreditCard = {
      number: "5274818933667191",
      cvv: "456",
      validateDate: "28/07/2020"
    }

    try {
      await confirmCheckoutUseCase.execute(cart, creditCard);
    } catch (e) {
      expect(e.message).toMatch(new InvalidCardError().message);
    }
  });

  it('should throw InsuficientStockError when CheckoutStatus is InsuficientStock', async () => {
    checkoutRepository.checkoutResult.status = CheckoutStatus.InsuficientStock;
    checkoutRepository.checkoutResult.itemsWithInsuficientStock = [ product ]

    const cart: TCart = new TCart();
    cart.add(product, 5);
    const creditCard: TCreditCard = {
      number: "5274818933667191",
      cvv: "456",
      validateDate: "28/07/2020"
    }

    try {
      await confirmCheckoutUseCase.execute(cart, creditCard);
    } catch (e) {
      expect(e.itemsWithInsuficientStock).toEqual([product]);
      expect(e.message).toMatch(new InsuficientStockError([product]).message);
    }
  });
})