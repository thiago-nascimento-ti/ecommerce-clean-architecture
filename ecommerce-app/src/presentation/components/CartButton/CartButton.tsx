import React from "react";
import CartButtonView from "./CartButtonView";
import { selectCart } from "../../app/slices/cart";
import { useAppSelector } from "../../app/hooks";
import useRoutes from "../../hooks/useRoutes";

type Props = {}
const CartButton: React.FC<Props> = () => {
  const cart = useAppSelector(selectCart);
  const routes = useRoutes();
  const onClick = routes.cart().go;

  return <CartButtonView {...{ itemAmount: cart.amount, onClick }}/>
};

export default CartButton;