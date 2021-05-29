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
import { Modal } from 'antd';

const ProductList: React.FC<RouterProps> = ({ history }) => {
  const dispatch = useAppDispatch();
  const pagination: TPagination = usePagination(8);
  const productList: TProductList = useProductList(pagination);

  const showInsuficientItemStockModal = (error: InsuficientItemStockError) => {
    Modal.error({
      title: error.title,
      content: error.content,
      okText: "confirmar"
    });
  }

  const onPressProduct = ({ id, name }: TProduct) => {
    history.push(routes.product(String(id), name))
  };

  const onAddToCart = (product: TProduct) => {
    dispatch(increment({product, amount: 1}, showInsuficientItemStockModal))
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
