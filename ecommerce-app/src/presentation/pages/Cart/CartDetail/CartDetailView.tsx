import React from "react";
import { Button, Input, Empty } from "antd";
import { styled } from "./CartDetailStyled";
import { Table, Image, Space } from 'antd';
import { brPriceFormat } from "../../../components/BRPrice"

type Props = {
  data: Array<TRecord>
  pageSize: number;
};
const CartDetailView: React.FC<Props> = ({ data, pageSize }) => {
  return (
    <>
      <p style={styled.title}>Minha Cesta</p>
      <Table 
        columns={columns} 
        dataSource={data} 
        pagination={{pageSize}}
        locale={{
          emptyText: <Empty description="Sua cesta está vazia"/>
        }} 
      />
    </>
  );
};

export default CartDetailView;

export type TRecord = {
  image: string
  name: string
  amount: number
  payable: number
  onDecrement: () => void
  onIncrement: () => void
  onAmountChange: (value: string) => void
  onRemove: () => void
}

const columns = [
  {
    title: 'Produto',
    dataIndex: 'name',
    key: 'name',
    render: (text: string, record: TRecord) => (
      <Space size="middle">
        <Image src={record.image} width={75}/>
        {record.name}
      </Space>
    ),
  },
  {
    title: 'Qtd.',
    dataIndex: 'amount',
    key: 'amount',
    align: "center" as "center",
    width: styled.columns.amount.width,
    render: (value: number, record: TRecord) => (
      <>
        <div>
          <Button style={styled.inputButton} onClick={() => record.onDecrement()}>-</Button>
          <Input style={styled.input} onChange={e => record.onAmountChange(e.target.value)} value={value}/>
          <Button style={styled.inputButton} onClick={() => record.onIncrement()}>+</Button>
        </div>
        <Button style={styled.removeButton} type="text" onClick={() => record.onRemove()}>Remover produto</Button>
      </>
    )
  },
  {
    title: 'Preço',
    dataIndex: 'payable',
    key: 'payable',
    width: styled.columns.price.width,
    align: "center" as "center",
    render: (value: number) => {
      return <p style={styled.price}>{brPriceFormat(value)}</p>
    }
  }
]