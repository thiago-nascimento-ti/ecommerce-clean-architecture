import React from "react";
import { Layout, Row } from "antd";
import { styled } from "./ReviewLayoutStyled";

const { Content } = Layout;

type Props = {
  children: React.ReactNode
};
const ReviewLayoutView: React.FC<Props> = ({ children }) => {
  return (
    <Content style={styled.content}>
      <Row justify="center">
        {children}
      </Row>
    </Content>
  );
};

export default ReviewLayoutView;