import React from "react";
import { Col } from "antd";
import { styled } from "./ReviewStyled";

type Props = {
  children: React.ReactNode
};
const ReviewView: React.FC<Props> = ({ children }) => {
  return (
    <Col span={5} style={styled.col}>
      {children}
    </Col>
  );
};

export default ReviewView;