import React from "react";
import { PageHeader } from "antd";
import CartButton from "../CartButton";

type Props = {}
const HeaderView: React.FC<Props> = () => {
  return (
    <>
      <PageHeader
        ghost={false}
        title="Ecommerce"
        subTitle="best place to buy"
        extra={[<CartButton key="1"/>]}
      />
      <div className="ant-layout-header" />
    </>
  );
};

export default HeaderView;