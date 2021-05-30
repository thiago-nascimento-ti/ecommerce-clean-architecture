import React from "react";
import { ICart } from '../../../../domain/entities';
import useRoutes from '../../../hooks/useRoutes';
import CheckoutReviewView from "./CheckoutReviewView";

type Props = {
  cart: ICart
};
const CheckoutReview: React.FC<Props> = ({ cart }) => {
  const routes = useRoutes();
  const goToHome = routes.home().go;
  const goToCheckout = routes.checkout().go;

  return <CheckoutReviewView {...{ cart, goToHome, goToCheckout }}/>
};

export default CheckoutReview;