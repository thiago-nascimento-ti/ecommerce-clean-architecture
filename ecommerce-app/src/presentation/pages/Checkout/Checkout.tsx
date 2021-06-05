import React from "react";
import CheckoutView from "./CheckoutView";
import { selectCart } from "../../app/slices/cart";
import { useAppSelector } from "../../app/hooks";  
import useRoutes from "../../hooks/useRoutes";

type Props = {};
const Checkout: React.FC<Props> = () => {
  const cart = useAppSelector(selectCart);
  const routes = useRoutes();
  if (cart.amount === 0) {
    routes.home().go();
  }

  return <CheckoutView {...{ cart }} />;
};

export default Checkout;