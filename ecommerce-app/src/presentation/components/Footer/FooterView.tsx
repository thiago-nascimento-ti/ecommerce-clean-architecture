import React from "react";
import { Layout } from "antd";

const { Footer } = Layout;

type Props = {}
const FooterView: React.FC<Props> = () => {
  return (
    <Footer style={{ textAlign: "center" }}>
      Ecommerce ©2021 Created by Thiago Nascimento
    </Footer>
  );
};

export default FooterView;
