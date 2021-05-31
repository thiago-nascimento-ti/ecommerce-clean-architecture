import React, { ReactNode } from "react";
import Footer from "../Footer";
import Header from "../Header";
import Breadcrumb from "../Breadcrumb";
import { Layout, Row, Col } from "antd";
import { styled } from "./StructureStyled";

const { Content } = Layout;

type Props = {
  children: ReactNode
};
const StructureView: React.FC<Props> = ({ children }) => {
  return (
    <Layout style={styled.layout}>
      <Header/>
      <Row justify="center">
        <Col xs={24} sm={24} xl={23}>
          <Content>
            <Breadcrumb/>
            {children}
          </Content>
        </Col>
      </Row>
      <Footer/>
    </Layout>
  );
};

export default StructureView;
