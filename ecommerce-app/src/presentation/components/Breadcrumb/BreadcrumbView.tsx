import React from "react";
import { Breadcrumb } from "antd";

type Props = {
  items: Array<string>
}
const BreadcrumbView: React.FC<Props> = ({ items = []}) => {
  return (
    <Breadcrumb style={{ margin: "16px 0" }}>
      {items.map((item, key) => {
        return <Breadcrumb.Item key={key}>{item}</Breadcrumb.Item>;
      })}
    </Breadcrumb>
  );
};

export default BreadcrumbView;
