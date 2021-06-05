import React from "react";
import { ICart } from "../../../domain/entities";
import CartReview from "./CartReview";
import CartDetail from "./CartDetail";
import ReviewLayout, { Detail, Review} from "../../components/ReviewLayout";

type Props = {
  cart: ICart
};
const CartView: React.FC<Props> = ({ cart }) => {
  return (
    <ReviewLayout>
        <Detail>
          <CartDetail {...{ cart }}/>
        </Detail> 
        <Review>
          <CartReview {...{ cart }}/>
        </Review>
    </ReviewLayout>
  );
};

export default CartView;