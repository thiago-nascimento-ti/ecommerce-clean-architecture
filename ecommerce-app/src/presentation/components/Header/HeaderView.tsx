import React from "react";
import { PageHeader, Button, Badge } from "antd";
import { ShoppingCartOutlined } from "@ant-design/icons";

type Props = {
  itemAmount?: number
}
const HeaderView: React.FC<Props> = ({ itemAmount = 5 }) => {
  return (
    <>
      <PageHeader
        ghost={false}
        title="Ecommerce"
        subTitle="best place to buy"
        extra={[
          <Badge key="1" count={itemAmount} overflowCount={999}>
            <Button size="large" icon={<ShoppingCartOutlined />}>
              Checkout
            </Button>
          </Badge>,
        ]}
      />
      <div className="ant-layout-header" />
    </>
  );
};

export default HeaderView;