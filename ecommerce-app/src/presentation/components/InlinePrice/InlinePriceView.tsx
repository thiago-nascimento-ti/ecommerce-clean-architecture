import React from "react";
import BRPrice from "../BRPrice";
import { Styled } from "./InlinePriceStyled";

type Props = {
  label: string,
  price: number,
  color: string,
  fontSize: string,
  fontWeight?: number 
};
const InlinePrice: React.FC<Props> = ({ 
  label, 
  price, 
  color, 
  fontSize, 
  fontWeight 
}) => {
  const styled = Styled(color, fontSize, fontWeight);
  
  return (
    <div style={styled.div}>
      <p style={styled.p}>{label}</p>
      <BRPrice style={styled.p} value={price}/>
    </div>
  )
}

export default InlinePrice;