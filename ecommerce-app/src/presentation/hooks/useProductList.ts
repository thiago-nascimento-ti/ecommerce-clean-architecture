import { useState, useEffect } from "react";
import { TProductList, TPagination } from "../../domain/repositories";
import { Factory } from "../../gateway/factory";

const productRepository = Factory.createProductRepository();
export default function useProductList({ currentPage, maxItemsPerPage }: TPagination): TProductList {
  const initialState: TProductList = {products: [], count: 0};
  const [productList, setProductList] = useState<TProductList>(initialState);

  useEffect(() => {
    productRepository.findAllProducts({ currentPage, maxItemsPerPage }).then(productList => {
      setProductList(productList);
    })
  }, [currentPage, maxItemsPerPage]);

  return productList;
}