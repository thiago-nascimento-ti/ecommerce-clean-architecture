import React from "react";
import { Row, Col } from "antd";
import { TProduct } from "../../../../domain/entities";
import { styled } from "./ProductDetailStyled";

type Props = {
  product: TProduct
};
const ProductDetailView: React.FC<Props> = ({ product }) => { 
  return (
    <Row justify="center">
      <Col span={12} style={styled.col}>
        <img style={styled.img} alt={product.name} src={product.image}  />
      </Col>
      <Col span={12} style={styled.col}>
        <h2>{product.name}</h2>
        <p>({product.id})</p>
        <p>{product.description}</p>
      </Col>
    </Row>
  )
} 

export default ProductDetailView;