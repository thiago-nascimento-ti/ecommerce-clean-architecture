import { ICart, TCart, TItem, TProduct } from "../../domain/entities";
import { CartJsonAdapter } from "./CartJsonAdapter";

describe('Cart Json Adapter tests', () => {
  it('should convert TCart to Json', () => {
    const cart: TCart = new TCart();
    for (let index = 0; index < 2; index++) {
      const product: TProduct = {id: index, name: `teste ${index}`, description: "", price: 10, image: "", rate: 3, maxParcels: 1}
      cart.add(product, 1);
    }
    
    const result: ICart = new CartJsonAdapter().toJson(cart);

    expect(result.items.length).toBe(cart.items.length);
    expect(result.amount).toBe(cart.amount)
    expect(result.payable).toBe(cart.payable)
    expect(result.items[0] instanceof TItem).toBeFalsy()
    expect(result.items[0] instanceof TItem).toBeFalsy()
  });

  it('should convert Json to TCart', () => {
    const cart: ICart = {
      items: [],
      payable: 20,
      amount: 2
    };
    for (let index = 0; index < 2; index++) {
      const product: TProduct = {id: index, name: `teste ${index}`, description: "", price: 10, image: "", rate: 3, maxParcels: 1}
      cart.items.push({product, amount: 1})
    }
    
    const result: TCart = new CartJsonAdapter().fromJson(cart);

    expect(result.items.length).toBe(cart.items.length);
    expect(result.amount).toBe(cart.amount)
    expect(result.payable).toBe(cart.payable)
    expect(result.items[0] instanceof TItem).toBeTruthy()
    expect(result.items[0] instanceof TItem).toBeTruthy()
  });
})