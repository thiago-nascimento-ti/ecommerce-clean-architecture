import React from "react";
import { ICart } from '../../../../domain/entities';
import useRoutes from '../../../hooks/useRoutes';
import CartReviewView from "./CartReviewView";

type Props = {
  cart: ICart
};
const CartReview: React.FC<Props> = ({ cart }) => {
  const routes = useRoutes();
  const goToHome = routes.home().go;
  const goToCheckout = routes.checkout().go;

  const maxParcels = cart.items.reduce(
    (max, {product:{maxParcelas}}) => max < maxParcelas ? maxParcelas : max,
  1)

  return <CartReviewView {...{ cart, goToHome, goToCheckout, maxParcels }}/>
};

export default CartReview;