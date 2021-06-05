import React from "react";
import { Rate, Button } from "antd";
import { TProduct } from "../../../../domain/entities";
import { styled } from "./ProductReviewStyled";

type Props = {
  product: TProduct,
  onAddToCart: (product: TProduct) => void
  goToHome: () => void
};

const ProductReviewView: React.FC<Props> = ({ product, onAddToCart, goToHome }) => { 
  return (
    <>
      <p style={styled.price}>R$ {product.price.toFixed(2).replace(".", ",")}</p>
      <p>em {product.maxParcels}x sem juros no cartão de crédito</p>
      <Rate value={product.rate} disabled />
      <Button style={styled.button} type="primary" onClick={() => onAddToCart(product)}>
        Adicionar ao carrinho
      </Button>
      <Button style={styled.button} onClick={goToHome}>
        Escolher outros produtos
      </Button>
    </>
  )
} 

export default ProductReviewView;