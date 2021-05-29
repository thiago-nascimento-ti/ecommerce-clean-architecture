import React from "react";
import ProductList from "./ProductList";
import { Layout } from "antd";

const { Content } = Layout;

type Props = {};
const HomeView: React.FC<Props> = () => {
  return (
    <Content style={{ padding: "0 24px", minHeight: 280 }}>
      <ProductList />
    </Content>
  );
};

export default HomeView;