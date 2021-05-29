import React from "react";
import ProductCard from "../../../components/ProductCard";
import { TProduct } from "../../../../domain/entities";
import { TProductList, TPagination } from "../../../../domain/repositories";
import { Col, Row, Pagination } from "antd";
import { styled } from "./ProductListStyled";

type Props = {
  productList: TProductList,
  pagination: TPagination,
  onPressProduct: (product: TProduct) => void
  onAddToCart: (product: TProduct) => void
};
const ProductListView: React.FC<Props> = ({
  productList: { products, count },
  pagination: {currentPage, setCurrentPage, maxItemsPerPage},
  onPressProduct,
  onAddToCart,
}) => {
  return (
    <div className="site-card-wrapper">
      <Row style={styled.rowProducts} gutter={[80, 80]} justify="center">
        {products.map((product, key) => {
          return (
            <Col key={key}>
              <ProductCard {...{ product, onPressProduct, onAddToCart }} />
            </Col>
          );
        })}
      </Row>
      <Row style={styled.rowPagination} justify="center">
        <Col>
        <Pagination
              current={currentPage}
              total={count}
              pageSize={maxItemsPerPage}
              onChange={setCurrentPage}
            />
        </Col>
      </Row>
    </div>
  );
};

export default ProductListView;

