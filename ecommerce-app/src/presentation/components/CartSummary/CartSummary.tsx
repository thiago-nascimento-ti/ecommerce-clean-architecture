import React from "react";
import { ICart } from '../../../domain/entities';
import CartSummaryView from "./CartSummaryView";

type Props = {
  cart: ICart
};
const CartSummary: React.FC<Props> = ({ cart }) => {
  const maxParcels = cart.items.reduce(
    (max, {product:{maxParcelas}}) => max < maxParcelas ? maxParcelas : max,
  1)

  return <CartSummaryView {...{ cart, maxParcels }}/>
};

export default CartSummary;