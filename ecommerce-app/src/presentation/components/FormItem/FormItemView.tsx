import React from "react";
import { Form } from "antd";
import { 
  Controller, 
  Control, 
  ControllerRenderProps, 
  ControllerFieldState,
  UseFormStateReturn, 
  DeepMap, 
  FieldError 
} from "react-hook-form";

type Props = {
  name: string 
  label: string
  control: Control<any>
  errors: DeepMap<any, FieldError>
  render: ({ field, fieldState, formState }: {
    field: ControllerRenderProps<any, string>
    fieldState: ControllerFieldState
    formState: UseFormStateReturn<any>
  }) => React.ReactElement<any, string>
}
const FormItemView: React.FC<Props> = ({
  name,
  label, 
  control,
  errors,
  render
}) => {
  const { message } = errors[name] || {};
  const status = message ? "error" : undefined;
  return (
      <Controller 
        name={name} 
        control={control}
        render={args => (
          <Form.Item 
            label={label}
            validateStatus={status}
            help={message}
          >
            {render(args)}
          </Form.Item>
        )}
      />
    
  )

};

export default FormItemView;