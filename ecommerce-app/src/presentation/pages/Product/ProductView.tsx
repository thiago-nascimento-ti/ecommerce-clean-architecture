import React from "react";
import { TProduct } from "../../../domain/entities";
import ProductDetail from "./ProductDetail";
import ProductReview from "./ProductReview";
import LayoutReview, { Review, Detail } from "../../components/ReviewLayout";

type Props = {
  product: TProduct | null
};
const ProductView: React.FC<Props> = ({ product }) => {
  if (product === null) {
    return null;
  }
  
  return (
    <LayoutReview>
        <Detail>
          <ProductDetail product={product}/>
        </Detail>
        <Review>
          <ProductReview product={product}/>
        </Review>
    </LayoutReview>
  );
};

export default ProductView;