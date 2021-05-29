import React from "react";
import { Layout } from "antd";
import { TProduct } from "../../../domain/entities";

const { Content } = Layout;

type Props = {
  product: TProduct | null
};
const ProductView: React.FC<Props> = ({ product }) => {
  if (product === null) {
    return null;
  }

  return (
    <Content style={{ padding: "0 24px", minHeight: 280 }}>
      {product.name}
    </Content>
  );
};

export default ProductView;