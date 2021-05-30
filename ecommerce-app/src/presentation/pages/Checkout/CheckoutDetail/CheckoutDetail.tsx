import React from "react";
import { ICart } from '../../../../domain/entities';
import { InvalidCardError, InsuficientStockError } from '../../../../domain/usecases';
import CheckoutDetailView, { TFormData } from "./CheckoutDetailView";
import useRoutes from "../../../hooks/useRoutes";
import * as Yup from 'yup';
import { useForm } from "react-hook-form";
import { yupResolver } from '@hookform/resolvers/yup';
import { checkout } from "../../../app/slices/cart";
import { useAppDispatch } from '../../../app/hooks';
import { showErrorModal, showSuccessModal } from "../../../components/Message/Message";

const requiredText = "Esse campo é obrigatório";
const creditCardMinMaxText = "O número do cartão deve conter 16 dígitos";
const nameLengthText = "O máximo de caracteres é 200";
const ccvMinMaxText = "O CCV deve conter 3 dígitos";

const schema: Yup.SchemaOf<TFormData> = Yup.object().shape({
  cardNumber: Yup
    .string()
    .required(requiredText)
    .min(16, creditCardMinMaxText)
    .max(16, creditCardMinMaxText)
    .transform(value => (
      value ? value.replace(/[^0-9]/gi, "") : ""
    )).default("1234567890123456"),
  name: Yup
    .string()
    .required(requiredText)
    .max(200, nameLengthText).default("Thiago"),
  validateDate: Yup
    .string()
    .typeError(requiredText)
    .required(requiredText),
  cvv: Yup
    .string()
    .required(requiredText)
    .min(3, ccvMinMaxText)
    .max(3, ccvMinMaxText).default("123")
    .transform(value => value ? value : ""),
  parcelAmount: Yup
    .number()
    .default(1)
    .required(requiredText),
}).defined();

type Props = {
  cart: ICart
};
const CheckoutDetail: React.FC<Props> = ({ cart }) => {
  const routes = useRoutes();
  const dispatch = useAppDispatch();
  const { handleSubmit, control, formState: { errors }, reset } = useForm<TFormData>({
    defaultValues: schema.getDefault(),
    resolver: yupResolver(schema)
  });

  const onSuccessful = () => {
    showSuccessfulCheckout(routes.home().go)
  }

  const onInvalidCreditCard = (error: InvalidCardError) => {
    showInvalidCreditCard(() => {
      reset(schema.getDefault())
    })
  }

  const onInsuficientStock = (error: InsuficientStockError) => {
    showInsuficientStock()
  }

  const onSubmit = handleSubmit(values => {
    console.log(values)
    const date = new Date(values.validateDate);
    const validateDate = `${date.getFullYear()}-${date.getMonth()+1}`;
    const creditCart = {...values, validateDate};

    dispatch(checkout(
      creditCart,
      onSuccessful,
      onInvalidCreditCard,
      onInsuficientStock
    ));
  });

  const maxParcels = cart.items.reduce(
    (max, {product:{maxParcelas}}) => max < maxParcelas ? maxParcelas : max,
  1)
  const parcels: Array<number> = []
  for (let parcel = 1; parcel <= maxParcels; parcel++) {
    parcels.push(parcel)
  }

  return <CheckoutDetailView {...{control, onSubmit, parcels, errors}}/>
};

function showSuccessfulCheckout(afterClose: () => void) {
  showSuccessModal({
    title: "Compra finalizada!", 
    content: "Parabéns sua compra foi finalizada com sucesso!"
  }, afterClose);
}

function showInvalidCreditCard(afterClose: () => void) {
  showErrorModal({
    title: "Cartão inválido", 
    content: "Seu cartão está inválido, por favor preencha novamente."
  }, afterClose);
}

function showInsuficientStock() {
  showErrorModal({
    title: "Produtos sem estoque", 
    content: "Alguns produtos estão fora do estoque, por favor remova-os e tente novamente."
  });
}

export default CheckoutDetail;