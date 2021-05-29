import React from "react";
import { Col, Rate, Button } from "antd";
import { TProduct } from "../../../../domain/entities";
import { styled } from "./ProductPricesStyled";

type Props = {
  product: TProduct,
  onAddToCart: (product: TProduct) => void
};
const ProductPricesView: React.FC<Props> = ({ product, onAddToCart }) => { 
  return (
    <Col span={5} style={styled.col}>
      <p style={styled.price}>R$ {product.price.toFixed(2).replace(".", ",")}</p>
      <p>em {product.maxParcelas}x sem juros no cartão de crédito</p>
      <Rate value={product.rate} disabled />
      <Button style={styled.button} onClick={() => onAddToCart(product)}>
        Adicionar ao carrinho
      </Button>
    </Col>
  )
} 

export default ProductPricesView;