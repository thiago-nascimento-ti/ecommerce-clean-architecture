import { TProduct } from "../entities";

export interface IProductRepository {
  findAllProducts: (pagination: TPagination) => Promise<TProductList>;
  findProductById: (id: number) => Promise<TProduct>;
  getProductStock: (product: TProduct) => Promise<number>;
}

export type TProductList = {
  products: Array<TProduct>
  count: number;
}

export type TPagination = {
  currentPage: number;
  maxItemsPerPage: number;
  setCurrentPage?: (page: number) => void; 
  setMaxItemsPerPage?: (maxItemsPerPage: number) => void;
}