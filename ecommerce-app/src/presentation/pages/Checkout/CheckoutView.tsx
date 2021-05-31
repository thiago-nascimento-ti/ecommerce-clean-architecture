import React from "react";
import { ICart } from '../../../domain/entities';
import ReviewLayout, { Detail, Review} from "../../components/ReviewLayout";
import CheckoutDetail from "./CheckoutDetail";
import CartSummary from "../../components/CartSummary";

type Props = {
  cart: ICart
};
const CheckoutView: React.FC<Props> = ({ cart }) => {
  return (
    <ReviewLayout>
        <Detail>
          <CheckoutDetail {...{cart}}/>
        </Detail> 
        <Review>
          <CartSummary {...{cart}}/>
        </Review>
    </ReviewLayout>
  );
};

export default CheckoutView;