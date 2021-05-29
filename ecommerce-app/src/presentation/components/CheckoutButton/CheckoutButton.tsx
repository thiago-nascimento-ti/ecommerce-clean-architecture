import React from "react";
import CheckoutButtonView from "./CheckoutButtonView";
import { selectCart } from '../../app/slices/cart';
import { useAppSelector } from '../../app/hooks';
import useRoutes from '../../hooks/useRoutes';

type Props = {}
const CheckoutButton: React.FC<Props> = () => {
  const cart = useAppSelector(selectCart);
  const routes = useRoutes();
  const onClick = routes.cart().go;

  return <CheckoutButtonView {...{ itemAmount: cart.amount, onClick }}/>
};

export default CheckoutButton;