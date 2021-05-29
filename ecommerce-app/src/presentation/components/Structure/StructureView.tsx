import React, { ReactNode } from "react";
import Footer from "../Footer";
import Header from "../Header";
import Breadcrumb from "../Breadcrumb";
import { Layout } from "antd";
import { styled } from "./StructureStyled";

const { Content } = Layout;

type Props = {
  children: ReactNode
};
const StructureView: React.FC<Props> = ({ children }) => {
  return (
    <Layout style={styled.layout}>
      <Header />
      <Content style={styled.content}>
        <>
        <Breadcrumb/>
        {children}
        </>
      </Content>
      <Footer />
    </Layout>
  );
};

export default StructureView;
