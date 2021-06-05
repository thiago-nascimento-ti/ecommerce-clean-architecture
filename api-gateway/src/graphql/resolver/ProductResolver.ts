import { TProduct } from "../../domain/entities";
import { TPagination, TProductList } from "../../domain/repositories";
import { Factory } from "../../gateway/factory";
import { Resolver } from "./Resolver";

const productRepository = Factory.createProductRepository();

const schema = `
  products(page: Int, limit: Int): PagedProduct
  product(id: Long): Product
`; 

const query = {
  products: async (obj: any, pagination: TPagination): Promise<TProductList> => {
    return await productRepository.findAllProducts(pagination);
  },
  product: async (obj: any, params: TProductByIdFilter): Promise<TProduct> => {
    const { id } = params;
    return await productRepository.findProductById(id);
  },
};

const resolver: Resolver = {
  mutations: null,
  queries: {
    schema,
    query 
  }
}
export default resolver

type TProductByIdFilter = {
  id: number
}