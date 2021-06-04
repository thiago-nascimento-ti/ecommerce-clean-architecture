import { IProductRepository, ICheckoutRepository } from "../../domain/repositories";
import { 
  AddProductOnCartUseCase, 
  ConfirmCheckoutUseCase, 
  FindAllProductsUseCase, 
  SubtractProductOfCartUseCase 
} from "../../domain/usecases";
import { ProductRestRepository, CheckoutRestRepository } from "../repositories";
import { AxiosHttpClient, IHttpClient } from "../http";
import { CartJsonAdapter } from "../adapters";

export class Factory {

  static createHttpCLient(): IHttpClient {
    const host = process.env.REACT_APP_SERVER_HOST!!;
    return new AxiosHttpClient(host);
  }

  static createProductRepository(): IProductRepository {
    return new ProductRestRepository(Factory.createHttpCLient());
  }

  static createCheckoutRestRepository(): ICheckoutRepository {
    return new CheckoutRestRepository(Factory.createHttpCLient());
  }

  static createCartJsonAdapter(): CartJsonAdapter  {
    return new CartJsonAdapter();
  }
  
  static createAddProductOnCartUseCase(): AddProductOnCartUseCase  {
    return new AddProductOnCartUseCase(Factory.createProductRepository());
  }

  static createConfirmCheckoutUseCase(): ConfirmCheckoutUseCase  {
    return new ConfirmCheckoutUseCase(Factory.createCheckoutRestRepository());
  }

  static createFindAllProductsUseCase(): FindAllProductsUseCase  {
    return new FindAllProductsUseCase(Factory.createProductRepository());
  }

  static createSubtractProductOfCartUseCase(): SubtractProductOfCartUseCase  {
    return new SubtractProductOfCartUseCase();
  }

}