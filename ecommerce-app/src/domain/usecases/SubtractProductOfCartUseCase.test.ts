import { TCart, IItem, TProduct, SubtractItemAmountError } from '../entities';
import { AddProductOnCartUseCase } from './AddProductOnCartUseCase';
import { SubtractProductOfCartUseCase } from './SubtractProductOfCartUseCase';
import { FakeProductRepository } from '../repositories/ProductRepository.util';

describe('subtract product of cart use case', () => {
  const productRepository: FakeProductRepository = new FakeProductRepository();
  const addProductOnCartUseCase = new AddProductOnCartUseCase(productRepository);
  const subtractProductOfCartUseCase = new SubtractProductOfCartUseCase();

  beforeEach(() => {
    productRepository.stock = 100;
  });

  it('should recalculate when subtract product amount', async () => {
    const cart: TCart = new TCart();
    const product: TProduct = {id: 1, name: "product 01", description: "", price: 10, image: "", rate: 3}

    await addProductOnCartUseCase.execute(cart, product, 5);
    subtractProductOfCartUseCase.execute(cart, product, 2);

    expect(cart.items.length).toBe(1);
    expect(cart.payable).toBe(30);
    expect(cart.amount).toBe(3);
  });

  it('should remove product from cart when subtract product amount to zero', async () => {
    const cart: TCart = new TCart();
    const product: TProduct = {id: 1, name: "product 01", description: "", price: 10, image: "", rate: 3}

    await addProductOnCartUseCase.execute(cart, product, 5);
    subtractProductOfCartUseCase.execute(cart, product, 5);

    expect(cart.items.length).toBe(0);
    expect(cart.payable).toBe(0);
    expect(cart.amount).toBe(0);
  });

  it('should throw SubtractItemAmountError when try to remove not enough amount of product', async () => {
    const cart: TCart = new TCart();
    const product: TProduct = {id: 1, name: "product 01", description: "", price: 10, image: "", rate: 3}

    await addProductOnCartUseCase.execute(cart, product, 1);
  
    const item: IItem = cart.items[0];
    const amountToRemove: number = 2;
    expect(() => {
      subtractProductOfCartUseCase.execute(cart, product, amountToRemove);
    }).toThrow(new SubtractItemAmountError(item, amountToRemove));
  })
})