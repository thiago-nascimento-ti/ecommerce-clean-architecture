import { ICart, TProduct } from "../../../domain/entities"
import cartReducer, { increment, decrement, selectCart } from "./cart";
import { RootState } from "../store";
import configureMockStore from "redux-mock-store"
import thunk from "redux-thunk"

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
  const middlewares = [thunk]
  const mockStore = configureMockStore(middlewares)

  it('should handle with initial state', () => {
    expect(cartReducer(undefined, { type: 'unknown' })).toEqual({
      items: [],
      amount: 0,
      payable: 0
    });
  });

  it.skip('should handle with increment', () => {
    // PROBLEMA NO MOCK NÂO EXECUTA O DISPATCH
    const store = mockStore({ cart: initialState })
    const amount: number = 1;
    return store.dispatch(increment({product, amount})).then(() => {
      const cart = selectCart(store.getState());
      expect(cart.amount).toEqual(3);
    })
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
