import React from "react";
import { ICart } from '../../../domain/entities';
import ReviewLayout, { Detail, Review} from "../../components/ReviewLayout";
import CheckoutReview from "./CheckoutReview";
import CheckoutDetail from "./CheckoutDetail";

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
          <CheckoutReview {...{cart}}/>
        </Review>
    </ReviewLayout>
  );
};

export default CheckoutView;