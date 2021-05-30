import React from "react";

type Props = {
  value: number,
  style: any
};
const BRPrice: React.FC<Props> = ({ value, style }) => {
  return <p style={style}>R$ {brPriceFormat(value)}</p>
}

export const brPriceFormat = (value: number) => {
  return value.toFixed(2).replace(".", ",")
}

export default BRPrice;