import { TCart, TProduct } from "../entities";
import { AddProductOnCartUseCase, InsuficientItemStockError } from "./AddProductOnCartUseCase";
import { FakeProductRepository } from "../repositories/ProductRepository.util";

describe('add product on cart use case', () => {
  const productRepository: FakeProductRepository = new FakeProductRepository();
  const addProductOnCartUseCase: AddProductOnCartUseCase = new AddProductOnCartUseCase(productRepository);
  const product1: TProduct = {id: 1, name: "product 01", description: "", price: 10, image: "", rate: 3, maxParcels: 1}
  const product2: TProduct = {id: 2, name: "product 02", description: "", price: 5.5, image: "", rate: 3, maxParcels: 1}

  beforeEach(() => {
    productRepository.stock = 100;
  });

  it('should add product on cart and recalculate', async () => {
    const cart: TCart = new TCart();

    await addProductOnCartUseCase.execute(cart, product1, 5);

    expect(cart.items.length).toBe(1);
    expect(cart.payable).toBe(50);
    expect(cart.amount).toBe(5);
  });

  it('should add product on cart twice and recalculate', async () => {
    const cart: TCart = new TCart();

    await addProductOnCartUseCase.execute(cart, product1, 5);
    await addProductOnCartUseCase.execute(cart, product1, 5);

    expect(cart.items.length).toBe(1);
    expect(cart.payable).toBe(100);
    expect(cart.amount).toBe(10);
  });

  it('should add two diferent products on cart and recalculate', async () => {
    const cart: TCart = new TCart();

    await addProductOnCartUseCase.execute(cart, product1, 5);
    await addProductOnCartUseCase.execute(cart, product2, 1);

    expect(cart.items.length).toBe(2);
    expect(cart.payable).toBe(55.5);
    expect(cart.amount).toBe(6);
  });

  it('should throw InsuficientItemStockError when does not have enough amount', async () => {
    productRepository.stock = 5;
    const cart: TCart = new TCart();

    await addProductOnCartUseCase.execute(cart, product1, 5);
    try {
      await addProductOnCartUseCase.execute(cart, product1, 1)
    } catch (e) {
      expect(e.message).toMatch(new InsuficientItemStockError(product1, 6).message);
    }

    expect(cart.items.length).toBe(1);
    expect(cart.payable).toBe(50);
    expect(cart.amount).toBe(5);
  });
})