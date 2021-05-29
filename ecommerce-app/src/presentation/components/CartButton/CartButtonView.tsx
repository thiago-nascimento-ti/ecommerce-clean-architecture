import React from "react";
import { Button, Badge } from "antd";
import { ShoppingCartOutlined } from "@ant-design/icons";

type Props = {
  itemAmount?: number
  onClick: () => void
}
const CartButtonView: React.FC<Props> = ({ itemAmount = 0, onClick }) => {
  return (
    <Badge count={itemAmount} overflowCount={999}>
      <Button onClick={onClick} size="large" icon={<ShoppingCartOutlined />}>
        Carrinho
      </Button>
    </Badge>
  );
};

export default CartButtonView;