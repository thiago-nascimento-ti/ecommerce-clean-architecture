import React from "react";
import { ICart } from '../../../../domain/entities';
import { InsuficientItemStockError } from "../../../../domain/usecases";
import CartDetailView, { TRecord } from "./CartDetailView";
import { increment, decrement } from "../../../app/slices/cart";
import { useAppDispatch } from '../../../app/hooks';
import { showErrorModal } from "../../../components/Message/Message";

type Props = {
  cart: ICart
};
const CartDetail: React.FC<Props> = ({ cart }) => {
  const dispatch = useAppDispatch();
  const pageSize = 4;

  const createData = () => (
    cart.items.map(({amount, product}) => {
      const { name, price, image } = product;
  
      const onAmountChange = (value: string) => {
        const newAmount = toValidInt(value);
        if (newAmount === 0) {
          return 
        }
        if (isIncrementExpression(newAmount, amount)) {
          onIncrement(newAmount - amount)
        } else {
          onDecrement(amount - newAmount)
        }
      }
  
      const onIncrement = (amount: number = 1) => {
        dispatch(increment({product, amount}, () => {}, showInsuficientItemStockModal))
      }
  
      const onDecrement = (amount: number = 1) => {
        dispatch(decrement({product, amount}))
      }
  
      const onRemove = () => {
        dispatch(decrement({product, amount}))
      }
  
      return {
        image, 
        name,
        amount,
        payable: price * amount,
        onDecrement,
        onIncrement,
        onAmountChange,
        onRemove
      }
    })
  )

  const data: Array<TRecord> = createData();
  return <CartDetailView {...{data, pageSize}}/>;
};

function toValidInt(value: string): number {
  value = value.replace(/[^0-9]/gi, "");
  return value === "" ? 0 : parseInt(value);
}

function isIncrementExpression(newAmount: number, actualAmount: number): boolean {
  return newAmount > actualAmount;
}

function showInsuficientItemStockModal({title, content}: InsuficientItemStockError) {
  showErrorModal({title, content});
}

export default CartDetail;