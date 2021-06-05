import React from "react";
import { ICart } from "../../../../domain/entities";
import { Button } from "antd";
import { styled } from "./CartReviewStyled";
import CartSummary from "../../../components/CartSummary";

type Props = {
  cart: ICart
  goToHome: () => void
  goToCheckout: () => void
};
const CartReviewView: React.FC<Props> = ({ cart, goToHome, goToCheckout }) => {
  return (
    <>
      <CartSummary {...{ cart }}/>
      <Button style={styled.button} onClick={goToHome}>
        Adicionar mais produtos
      </Button>
      <Button style={styled.button} type="primary" onClick={goToCheckout}>
        Finalizar compra
      </Button>
    </>
  );
};

export default CartReviewView;