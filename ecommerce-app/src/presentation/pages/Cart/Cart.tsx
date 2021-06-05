import React from "react";
import CartView from "./CartView";
import { selectCart } from "../../app/slices/cart";
import { useAppSelector } from "../../app/hooks";  
  
type Props = {};
const Cart: React.FC<Props> = () => {
  const cart = useAppSelector(selectCart);

  return <CartView {...{ cart }} />;
};

export default Cart;