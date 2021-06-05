import React from "react";
import { TProduct } from "../../../../domain/entities";
import { InsuficientItemStockError } from "../../../../domain/usecases";
import ProductReviewView from "./ProductReviewView";
import { increment } from "../../../app/slices/cart";
import { useAppDispatch } from "../../../app/hooks";
import { showErrorModal, showSuccessNotification } from "../../../components/Message/Message";
import useRoutes from "../../../hooks/useRoutes";

type Props = {
  product: TProduct
};
const ProductReview: React.FC<Props> = ({ product }) => {
  const dispatch = useAppDispatch();
  const routes = useRoutes();

  const showInsuficientItemStockModal = ({title, content}: InsuficientItemStockError) => {
    showErrorModal({title, content});
  }

  const onResolveAddToCart = ({ name }: TProduct) => {
    const title = `Produto adicionado ao carrinho.`
    const content = name;
    showSuccessNotification({title, content})
  }

  const onAddToCart = (product: TProduct) => {
    dispatch(increment({product, amount: 1}, onResolveAddToCart, showInsuficientItemStockModal))
  };

  const goToHome = routes.home().go;

  return <ProductReviewView {...{product, onAddToCart, goToHome}}/>;
};

export default ProductReview;