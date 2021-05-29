import { IProductRepository, TPagination, TProductList } from "../repositories";

export class FindAllProductsUseCase {
  private repository: IProductRepository;

  constructor(repository: IProductRepository) {
    this.repository = repository;
  }

  execute(pagination: TPagination): Promise<TProductList> {
    return this.repository.findAllProducts(pagination);
  }

}