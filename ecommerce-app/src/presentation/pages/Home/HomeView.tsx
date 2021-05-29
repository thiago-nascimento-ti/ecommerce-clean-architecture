import React from "react";
import ProductList from "./ProductList";
import { Layout } from "antd";
import { styled } from "./HomeStyled";

const { Content } = Layout;

type Props = {};
const HomeView: React.FC<Props> = () => {
  return (
    <Layout
      className="site-layout-background"
      style={styled.layout}
    >
      <Content style={styled.content}>
        <ProductList />
      </Content>
    </Layout>
  );
};


export default HomeView;