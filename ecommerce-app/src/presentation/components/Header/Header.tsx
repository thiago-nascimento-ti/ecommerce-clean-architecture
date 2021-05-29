import React from "react";
import HeaderView from "./HeaderView";
import { selectCart } from '../../app/slices/cart';
import { useAppSelector } from '../../app/hooks';

type Props = {}
const Header: React.FC<Props> = () => {
  const cart = useAppSelector(selectCart);
  return <HeaderView {...{ itemAmount: cart.amount }}/>
};

export default Header;