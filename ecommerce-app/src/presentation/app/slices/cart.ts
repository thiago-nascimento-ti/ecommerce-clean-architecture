import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { ICart, TCart, TCreditCard, TProduct } from "../../../domain/entities"
import { InsuficientItemStockError, InvalidCardError, InsuficientStockError } from "../../../domain/usecases";
import { RootState, AppThunk } from "../store";
import { Factory } from "../../../gateway/factory";

const initialState: ICart = {
  items: [],
  amount: 0,
  payable: 0
};

type TCartAction = {
  product: TProduct,
  amount: number,
};

const adapter = Factory.createCartJsonAdapter();
const addProductOnCartUseCase = Factory.createAddProductOnCartUseCase(); 
const subtractProductOfCartUseCase = Factory.createSubtractProductOfCartUseCase(); 
const ConfirmCheckoutUseCase = Factory.createConfirmCheckoutUseCase(); 

export const slice = createSlice({
  name: 'cart',
  initialState: getFromLocalStorageOrInitialState(),
  reducers: {
    updateCart: (state, action: PayloadAction<ICart>) => {
      const newState = action.payload;
      saveToLocalStorage(newState);
      return newState;
    },
    decrement: (state, action: PayloadAction<TCartAction>) => {
      const {product, amount} = action.payload;

      const cart: TCart = adapter.fromJson(state);
      subtractProductOfCartUseCase.execute(cart, product, amount);
      const newState = adapter.toJson(cart);
      saveToLocalStorage(newState)
      return newState;
    }
  }
});

const { updateCart } = slice.actions;

export const increment = (
  { product, amount}: TCartAction,
  onResolve: (product: TProduct) => void,
  onInsuficientItemStock: (error: InsuficientItemStockError) => void 
): AppThunk => async (
  dispatch,
  getState
) => {
  const currentState = selectCart(getState());
  const cart: TCart = adapter.fromJson(currentState);
  try {
    await addProductOnCartUseCase.execute(cart, product, amount);
    const newState = adapter.toJson(cart);
    dispatch(updateCart(newState))
    onResolve(product);
  } catch (error) {
    if (error instanceof InsuficientItemStockError) {
      onInsuficientItemStock(error);
    }
  }
};

export const checkout = (
  creditCard: TCreditCard,
  onResolve: () => void,
  onInvalidCreditCard: (error: InvalidCardError) => void,
  onInsuficientStock: (error: InsuficientStockError) => void,
): AppThunk => async (
  dispatch,
  getState
) => {
  const currentState = selectCart(getState());
  const cart: TCart = adapter.fromJson(currentState);

  try {
    await ConfirmCheckoutUseCase.execute(cart, creditCard);
    dispatch(updateCart(initialState))
    onResolve();
  } catch(error) {
    if (error instanceof InvalidCardError) {
      onInvalidCreditCard(error)
    } else if (error instanceof InsuficientStockError) {
      onInsuficientStock(error)
    }
  } 
};

export const selectCart = (state: RootState) => state.cart;

export const { decrement } = slice.actions;;
export default slice.reducer;

function getFromLocalStorageOrInitialState() {
  const state = localStorage.getItem('cart');
  if (state) {
    return JSON.parse(state);
  }
  return initialState;
}

function saveToLocalStorage(cart: ICart) {
  const state = JSON.stringify(cart);
  localStorage.setItem('cart', state);
}