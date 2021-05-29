import React from "react";
import { Layout, Row } from "antd";
import { TProduct } from "../../../domain/entities";
import { styled } from "./ProductStyled";
import ProductDetail from './ProductDetail';
import ProductPrices from './ProductPrices';

const { Content } = Layout;

type Props = {
  product: TProduct | null
};
const ProductView: React.FC<Props> = ({ product }) => {
  if (product === null) {
    return null;
  }
  
  return (
    <Content style={styled.content}>
      <Row justify="center">
        <ProductDetail product={product}/>
        <ProductPrices product={product}/>
      </Row>
    </Content>
  );
};

export default ProductView;