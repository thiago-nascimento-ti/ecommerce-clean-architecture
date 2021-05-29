import React, { ReactNode } from "react";
import Footer from "../Footer";
import Header from "../Header";
import Breadcrumb from "../Breadcrumb";
import { Layout } from "antd";

const { Content } = Layout;

type Props = {
  children: ReactNode,
};
const StructureView: React.FC<Props> = ({ children }) => {
  return (
    <Layout>
      <Header />
      <Content style={{ padding: "0 50px" }}>
        <Breadcrumb items={["teste01", "teste02"]}></Breadcrumb>
        <Layout
          className="site-layout-background"
          style={{ padding: "24px 0", backgroundColor: "#FFFFFF" }}
        >
          {children}
        </Layout>
      </Content>
      <Footer />
    </Layout>
  );
};

export default StructureView;
