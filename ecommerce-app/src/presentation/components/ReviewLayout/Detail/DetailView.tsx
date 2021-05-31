import React from "react";
import { Col } from "antd";
import { styled } from "./DetailStyled";

type Props = {
  children: React.ReactNode
};
const DetailView: React.FC<Props> = ({ children }) => {
  return (
    <Col xs={24} sm={24} xl={14} xxl={13}>
      <div style={styled.div}>
        {children}
      </div>
    </Col>
  );
};

export default DetailView;