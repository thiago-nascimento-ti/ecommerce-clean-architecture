import React from "react";
import CheckoutButtonView from "./CheckoutButtonView";
import { selectCart } from '../../app/slices/cart';
import { useAppSelector } from '../../app/hooks';

type Props = {}
const CheckoutButton: React.FC<Props> = () => {
  const cart = useAppSelector(selectCart);
  return <CheckoutButtonView {...{ itemAmount: cart.amount }}/>
};

export default CheckoutButton;