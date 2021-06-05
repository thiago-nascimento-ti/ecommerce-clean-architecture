import { ICart, TProduct } from "../../../domain/entities"
import cartReducer, { decrement, selectCart } from "./cart";
import { RootState } from "../store";

describe('cart reducer', () => {
  const product: TProduct = {
    id: 1, 
    name: "teste", 
    description: "", 
    price: 10, 
    image: "", 
    rate: 3,
    maxParcels: 1
  }
  const initialState: ICart = {
    items: [{product, amount: 2}],
    amount: 2,
    payable: 0
  };

  it('should handle with initial state', () => {
    expect(cartReducer(undefined, { type: 'unknown' })).toEqual({
      items: [],
      amount: 0,
      payable: 0
    });
  });

  it('should handle with decrement', () => {
    const amount: number = 1;
    const actual = cartReducer(initialState, decrement({product, amount}));
    expect(actual.amount).toEqual(1);
  });

  it('should handle with selectorCart', () => {
    const state: RootState = {
      cart: initialState
    }
    const cart = selectCart(state);
    expect(cart).toEqual(initialState);
  });

});
