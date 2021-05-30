import React from "react";
import { ICart } from '../../../../domain/entities';
import CartSummary from "../../../components/CartSummary";

type Props = {
  cart: ICart
};
const CheckoutReviewView: React.FC<Props> = ({ cart }) => {
  return (
    <CartSummary {...{cart}}/>
  );
};

export default CheckoutReviewView;