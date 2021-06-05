import { TProduct } from "../../domain/entities"
import { IProductRepository, TPagination, TProductList } from "../../domain/repositories"
import { IHttpClient, HttpGet } from "../http";

export class ProductRestRepository implements IProductRepository {
  private httpClient: IHttpClient

  constructor(httpClient: IHttpClient) {
    this.httpClient = httpClient;
  }

  async findAllProducts({page, limit}: TPagination): Promise<TProductList> {
    const params: HttpGet.Params = {
      uri: `/products?_page=${page}&_limit=${limit}`
    };
    const response = await this.httpClient.get<Array<TProduct>>(params);
    const count: number = parseInt(response.headers["x-total-count"]);
    return {
      products: response.data,
      count
    }
  }

  async findProductById(id: number): Promise<TProduct> {
    const params: HttpGet.Params = {
      uri: `/products/${id}`
    };
    return await (await this.httpClient.get<TProduct>(params)).data;
  }

  async getProductStock({ id }: TProduct): Promise<number> {
    const params: HttpGet.Params = {
      uri: `/products/${id}`
    };
    const { stock } = (await this.httpClient.get<TProductStock>(params)).data;
    return stock;
  }
}

type TProductStock = {
  id: number
  stock: number
}