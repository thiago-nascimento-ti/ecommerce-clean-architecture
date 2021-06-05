import { TProduct } from "../entities";

export interface IProductRepository {
  findAllProducts: (pagination: TPagination) => Promise<TProductList>
  findProductById: (id: number) => Promise<TProduct>
}

export type TProductList = {
  products: Array<TProduct>
  count: number;
}

export type TPagination = {
  page: number;
  limit: number
}