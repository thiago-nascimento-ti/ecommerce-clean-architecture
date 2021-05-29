import React from "react";
import { Layout } from "antd";
import { styled } from "./CartStyled";

const { Content } = Layout;

type Props = {};
const CartView: React.FC<Props> = () => {
  return (
    <Content style={styled.content}>
      cart
    </Content>
  );
};

export default CartView;