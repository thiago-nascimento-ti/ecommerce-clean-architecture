import React from "react";
import { TProduct } from "../../../../domain/entities";
import { TProductList, TPagination } from "../../../../domain/repositories";
import { InsuficientItemStockError } from "../../../../domain/usecases";
import useProductList from "../../../hooks/useProductList";
import usePagination from "../../../hooks/usePagination";
import ProductListView from "./ProductListView";
import { increment } from '../../../app/slices/cart';
import { useAppDispatch } from '../../../app/hooks';
import { withRouter, RouterProps } from 'react-router-dom'
import { routes } from '../../Router';
import { showErrorModal, showSuccessNotification } from "../../../components/Message/Message";


const ProductList: React.FC<RouterProps> = ({ history }) => {
  const dispatch = useAppDispatch();
  const pagination: TPagination = usePagination(8);
  const productList: TProductList = useProductList(pagination);

  const showInsuficientItemStockModal = ({title, content}: InsuficientItemStockError) => {
    showErrorModal({title, content});
  }

  const onResolveAddToCart = ({ name }: TProduct) => {
    const title = `Produto adicionado ao carrinho.`
    const content = name;
    showSuccessNotification({title, content})
  }

  const onPressProduct = ({ id, name }: TProduct) => {
    history.push(routes.product(String(id), name))
  };

  const onAddToCart = (product: TProduct) => {
    dispatch(increment({product, amount: 1}, onResolveAddToCart, showInsuficientItemStockModal))
  };

  return (
    <ProductListView
      {...{
        productList,
        pagination,
        onPressProduct,
        onAddToCart,
      }}
    />
  );
};

export default withRouter(ProductList);
