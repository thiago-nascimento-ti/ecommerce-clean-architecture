import React from "react";
import { Col } from "antd";
import { styled } from "./ReviewStyled";

type Props = {
  children: React.ReactNode
};
const ReviewView: React.FC<Props> = ({ children }) => {
  return (
    <Col xs={24} sm={24} xl={10} xxl={6}>
      <div style={styled.div}>
        {children}
      </div>
    </Col>
  );
};

export default ReviewView;