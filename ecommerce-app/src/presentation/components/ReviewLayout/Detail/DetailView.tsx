import React from "react";
import { Col } from "antd";
import { styled } from "./DetailStyled";

type Props = {
  children: React.ReactNode
};
const DetailView: React.FC<Props> = ({ children }) => {
  return (
    <Col span={13} style={styled.col}>
      {children}
    </Col>
  );
};

export default DetailView;