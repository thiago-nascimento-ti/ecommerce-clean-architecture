import React from "react";
import { Input, Select, Row, Col, DatePicker, InputNumber, Button, Form } from "antd";
import { styled } from "./CheckoutDetailStyled";
import { Control, DeepMap, FieldError } from "react-hook-form";
import FormItem from "../../../components/FormItem";

const { Option } = Select;

const creditCardMask = (value: string): string => (
  value.replace(/[^0-9]/gi, "").split("").map((letter, index, array) => 
    ((index+1) % 4 === 0 && index+1 < array.length) ? letter+" " : letter
  ).join("")
)

type Props = {
  parcels: Array<number>
  onSubmit: () => void,
  control: Control<TFormData>
  errors: DeepMap<TFormData, FieldError>
}
const CheckoutDetailView: React.FC<Props> = ({
  parcels,
  onSubmit,
  control,
  errors
}) => (
  <Row justify="center">
    <Col xs={24} md={12}>
      <p style={styled.title}>Forma de pagamento</p>
      <Form layout="vertical">
        <FormItem 
          label="Número do cartão"
          name="cardNumber" 
          control={control}
          errors={errors}
          render={({
            field: { onChange, value }
          }) => {
            if (value) {
              value = creditCardMask(String(value));
            }
            return (
              <Input {...{
                onChange, 
                value, 
                maxLength: 19, 
                size: "large"
              }}/>
            )
          }}
        />
        <FormItem 
          label="Nome impresso no cartão"
          name="name" 
          control={control}
          errors={errors}
          render={({
            field: { onChange, value }
          }) => (
            <Input {...{
              onChange, value, 
              maxLength: 200, 
              size: "large"
            }}/>
          )}
        />
        <FormItem 
          label="Validade do cartão"
          name="validateDate" 
          control={control}
          errors={errors}
          render={({
            field: { onChange, value }
          }) => (
              <DatePicker {...{
                onChange,
                value, 
                maxLength: 200, 
                size: "large", 
                picker: "month", 
                style: styled.datePicker 
              }}/>
          )}
        />
        <FormItem 
          label="CVV"
          name="cvv" 
          control={control}
          errors={errors}
          render={({
            field: { onChange, value }
          }) => (
            <InputNumber {...{
              onChange, 
              value, 
              maxLength: 3, 
              size: "large", 
              style: styled.inputNumber
            }}/>
          )}
        />
        <FormItem 
          label="Parcelas"
          name="parcelAmount" 
          control={control}
          errors={errors}
          render={({
            field: { onChange, value }
          }) => (
            <Select
              placeholder="Selecione o número de parcelas"
              size="large"
              onChange={onChange}
              value={value}
            >
            {
              parcels.map((parcel, key) => (
                <Option key={key} value={parcel}>Parcelar em {parcel}x sem juros</Option>
              ))
            }
          </Select>
          )}
        />
        <Button onClick={onSubmit} style={styled.button} type="primary">
            Fechar pedido
        </Button>
      </Form>
    </Col>
  </Row>
)

export type TFormData = {
  cardNumber: any
  name: any
  validateDate: any
  cvv: any
  parcelAmount: any
};

export default CheckoutDetailView;