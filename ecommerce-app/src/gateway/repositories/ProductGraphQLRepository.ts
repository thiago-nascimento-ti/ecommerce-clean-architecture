import { TProduct } from "../../domain/entities"
import { IProductRepository, TPagination, TProductList } from "../../domain/repositories"
import { 
  GraphQLClient, 
  findPagedProductsPQ, 
  findProductByIdPQ, 
  findProductStockPQ, 
  TIdVariable, 
  TPagedVariables 
} from "../graphql";

export class ProductGraphQLRepository implements IProductRepository {
  private graphQLClient: GraphQLClient

  constructor(graphQLClient: GraphQLClient) {
    this.graphQLClient = graphQLClient;
  }

  async findAllProducts({currentPage, maxItemsPerPage}: TPagination): Promise<TProductList> {
    const query = findPagedProductsPQ.get({page: currentPage, limit: maxItemsPerPage});
    return (await this.graphQLClient.execute<TPagedProductsData, TPagedVariables>(query)).products;
  }

  async findProductById(id: number): Promise<TProduct> {
    const query = findProductByIdPQ.get({id});
    return (await this.graphQLClient.execute<TProductData, TIdVariable>(query)).product;
  }

  async getProductStock({ id }: TProduct): Promise<number> {
    const query = findProductStockPQ.get({id})
    const { stock } = (await this.graphQLClient.execute<TProductStockData, TIdVariable>(query)).product;
    return stock;
  }
}

type TProductStock = {
  stock: number
}

type TProductStockData = {
  product: TProductStock
}

type TProductData = {
  product: TProduct
}

type TPagedProductsData = {
  products: TProductList
}

