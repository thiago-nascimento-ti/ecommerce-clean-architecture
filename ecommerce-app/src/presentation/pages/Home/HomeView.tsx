import React from "react";
import ProductList from "./ProductList";
import { Layout } from "antd";
import { styled } from "./HomeStyled";

const { Content } = Layout;

type Props = {};
const HomeView: React.FC<Props> = () => {
  return (
    <Content style={styled.content}>
      <ProductList />
    </Content>
  );
};


export default HomeView;