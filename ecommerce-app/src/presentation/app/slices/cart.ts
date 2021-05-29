import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { ICart, TCart, TProduct } from '../../../domain/entities'
import { InsuficientItemStockError } from "../../../domain/usecases";
import { RootState, AppThunk } from '../store';
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

export const slice = createSlice({
  name: 'cart',
  initialState,
  reducers: {
    updateCart: (state, action: PayloadAction<ICart>) => {
      const newState = action.payload;
      return newState;
    },
    decrement: (state, action: PayloadAction<TCartAction>) => {
      const {product, amount} = action.payload;

      const cart: TCart = adapter.fromJson(state);
      subtractProductOfCartUseCase.execute(cart, product, amount);
      return adapter.toJson(cart);
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
export const selectCart = (state: RootState) => state.cart;

export const { decrement } = slice.actions;;
export default slice.reducer;
