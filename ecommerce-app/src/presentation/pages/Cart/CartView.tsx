import React from "react";
import { Layout } from "antd";

const { Content } = Layout;

type Props = {};
const CartView: React.FC<Props> = () => {
  return (
    <Content style={{ padding: "0 24px", minHeight: 280 }}>
      cart
    </Content>
  );
};

export default CartView;