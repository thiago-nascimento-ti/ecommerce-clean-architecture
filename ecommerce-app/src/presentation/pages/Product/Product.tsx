import React from "react";
import ProductView from "./ProductView";
import useProduct from "../../hooks/useProduct";
import { useParams } from "react-router-dom";

type UriParams = {
  id: string
}
type Props = {};
const Product: React.FC<Props> = () => {
  const { id } = useParams<UriParams>();
  const product = useProduct(parseInt(id));

  return <ProductView {...{product}}/>;
};

export default Product;