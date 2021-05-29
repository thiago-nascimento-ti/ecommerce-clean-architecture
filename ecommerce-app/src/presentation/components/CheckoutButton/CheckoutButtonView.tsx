import React from "react";
import { Button, Badge } from "antd";
import { ShoppingCartOutlined } from "@ant-design/icons";

type Props = {
  itemAmount?: number
}
const CheckoutButtonView: React.FC<Props> = ({ itemAmount = 0 }) => {
  return (
    <Badge count={itemAmount} overflowCount={999}>
      <Button size="large" icon={<ShoppingCartOutlined />}>
        Checkout
      </Button>
    </Badge>
  );
};

export default CheckoutButtonView;