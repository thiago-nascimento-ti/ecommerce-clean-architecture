import React from "react";
import ProductCard from "../../../components/ProductCard";
import { TProduct } from "../../../../domain/entities";
import { TProductList, TPagination } from "../../../../domain/repositories";
import { Col, Row, Pagination } from "antd";

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
      <Row gutter={[24, 24]}>
        {products.map((product, key) => {
          return (
            <Col key={key} span={6}>
              <ProductCard {...{ product, onPressProduct, onAddToCart }} />
            </Col>
          );
        })}
      </Row>
      <Pagination
        current={currentPage}
        total={count}
        pageSize={maxItemsPerPage}
        onChange={setCurrentPage}
      />
    </div>
  );
};

export default ProductListView;

