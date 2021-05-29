import React from "react";
import { Layout, Row, Col, Rate } from "antd";
import { TProduct } from "../../../domain/entities";
import { styled } from "./ProductStyled";

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
      <Row style={styled.row} justify="center">
        <Col span={13} style={styled.colLeft}>
          <Row style={styled.row} justify="center">
            <Col span={12} style={styled.colLeft}>
              <img style={styled.img} alt={product.name} src={product.image}  />
            </Col>
            <Col span={12} style={styled.colLeft}>
              <h2>{product.name}</h2>
              <p>({product.id})</p>
              <p>{product.description}</p>
            </Col>
          </Row>
        </Col>
        <Col span={5} style={styled.colRight}>
          <p style={styled.price}>R$ {product.price.toFixed(2).replace(".", ",")}</p>
          <p>em {product.maxParcelas}x sem juros no cartão de crédito</p>
          <Rate value={product.rate} disabled />
        </Col>
      </Row>
    </Content>
  );
};

export default ProductView;