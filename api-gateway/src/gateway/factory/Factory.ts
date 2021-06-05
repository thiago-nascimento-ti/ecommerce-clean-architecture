import { IProductRepository, ICheckoutRepository } from "../../domain/repositories";
import { ProductRestRepository, CheckoutRestRepository } from "../repositories";
import { AxiosHttpClient, IHttpClient } from "../http";

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

}