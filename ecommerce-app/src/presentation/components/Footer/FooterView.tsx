import React from "react";
import { Layout } from "antd";
import { styled } from "./FooterStyled";

const { Footer } = Layout;

type Props = {}
const FooterView: React.FC<Props> = () => {
  return (
    <Footer style={styled.footer}>
      Ecommerce ©2021 Created by Thiago Nascimento
    </Footer>
  );
};

export default FooterView;
