import React from "react";
import { Breadcrumb } from "antd";
import { styled } from "./BreadcrumbStyled";

type Props = {
  items: Array<string>
}
const BreadcrumbView: React.FC<Props> = ({ items = []}) => {
  return (
    <Breadcrumb style={styled.breadcrumb}>
      {items.map((item, key) => {
        return <Breadcrumb.Item key={key}>{item}</Breadcrumb.Item>;
      })}
    </Breadcrumb>
  );
};

export default BreadcrumbView;
