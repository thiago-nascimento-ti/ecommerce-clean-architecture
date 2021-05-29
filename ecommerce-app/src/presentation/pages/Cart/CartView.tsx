import React from "react";
import { ICart } from '../../../domain/entities';
import { Layout, Row, Col } from "antd";
import { styled } from "./CartStyled";
import CartReview from "./CartReview";
import CartDetail from "./CartDetail";

const { Content } = Layout;

type Props = {
  cart: ICart
};
const CartView: React.FC<Props> = ({ cart }) => {
  return (
    <Content style={styled.content}>
      <Row style={styled.row} justify="center">
        <CartDetail {...{ cart }}/> 
        <CartReview {...{ cart }}/>
      </Row>
    </Content>
  );
};

export default CartView;