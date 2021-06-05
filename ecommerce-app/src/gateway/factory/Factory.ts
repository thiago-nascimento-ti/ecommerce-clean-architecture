import { IProductRepository, ICheckoutRepository } from "../../domain/repositories";
import { 
  AddProductOnCartUseCase, 
  ConfirmCheckoutUseCase, 
  FindAllProductsUseCase, 
  SubtractProductOfCartUseCase 
} from "../../domain/usecases";
import { ProductGraphQLRepository, CheckoutGraphQLRepository } from "../repositories";
import { AxiosHttpClient, IHttpClient } from "../http";
import { CartJsonAdapter } from "../adapters";
import { GraphQLClient } from "../graphql";

export class Factory {

  static createHttpCLient(): IHttpClient {
    const host = process.env.REACT_APP_SERVER_HOST!!;
    return new AxiosHttpClient(host);
  }

  static createGraphQLCLient(): GraphQLClient {
    return new GraphQLClient(Factory.createHttpCLient());
  }

  static createProductRepository(): IProductRepository {
    return new ProductGraphQLRepository(Factory.createGraphQLCLient());
  }

  static createCheckoutRepository(): ICheckoutRepository {
    return new CheckoutGraphQLRepository(Factory.createGraphQLCLient());
  }

  static createCartJsonAdapter(): CartJsonAdapter  {
    return new CartJsonAdapter();
  }
  
  static createAddProductOnCartUseCase(): AddProductOnCartUseCase  {
    return new AddProductOnCartUseCase(Factory.createProductRepository());
  }

  static createConfirmCheckoutUseCase(): ConfirmCheckoutUseCase  {
    return new ConfirmCheckoutUseCase(Factory.createCheckoutRepository());
  }

  static createFindAllProductsUseCase(): FindAllProductsUseCase  {
    return new FindAllProductsUseCase(Factory.createProductRepository());
  }

  static createSubtractProductOfCartUseCase(): SubtractProductOfCartUseCase  {
    return new SubtractProductOfCartUseCase();
  }

}