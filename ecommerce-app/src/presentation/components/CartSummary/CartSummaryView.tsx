import React from "react";
import { ICart } from "../../../domain/entities";
import { styled } from "./CartSummaryStyled";
import InlinePrice from "../InlinePrice";
import { Divider } from "antd";

type Props = {
  cart: ICart
  maxParcels: number
};
const CartSummaryView: React.FC<Props> = ({ cart, maxParcels }) => {
  const productLabel = cart.amount === 1 ? " produto" : " produtos";
  const { basicPrice, shippingPrice, finalPrice } = styled;
  
  return (
    <>
      <p style={styled.title}>Resumo do pedido</p>
      <InlinePrice 
        color={basicPrice.color} 
        fontSize={basicPrice.fontSize}
        label={cart.amount+productLabel} 
        price={cart.payable}
      />
      <InlinePrice 
        color={shippingPrice.color} 
        fontSize={shippingPrice.fontSize}
        label="Frete" 
        price={0}
      />
      <Divider style={styled.divider} orientation="left"/>
      <InlinePrice 
        color={finalPrice.color}
        fontSize={finalPrice.fontSize}
        fontWeight={finalPrice.fontWeight}
        label="Total" 
        price={cart.payable}
      />
      <p style={styled.parcel}>em até {maxParcels}x sem juros</p>
    </>
  );
};

export default CartSummaryView;