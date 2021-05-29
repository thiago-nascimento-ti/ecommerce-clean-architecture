import { TPagination } from "../repositories";
import { FakeProductRepository, MEMORY_PRODUCTS } from "../repositories/ProductRepository.util";
import { FindAllProductsUseCase } from "./FindAllProductsUseCase";

describe('find all products use case', () => {
  const productRepository: FakeProductRepository = new FakeProductRepository();

  beforeEach(() => {
    productRepository.stock = 100;
  });

  it('should get first page of repository', async () => {
    const pagination: TPagination = {
      currentPage: 1,
      maxItemsPerPage: 2
    }
    const useCase = new FindAllProductsUseCase(productRepository); 
    const {products, count} = await useCase.execute(pagination);

    expect(products.length).toBe(2);
    expect(count).toBe(7);
    expect(products[0].id).toBe(MEMORY_PRODUCTS[0].id);
    expect(products[1].id).toBe(MEMORY_PRODUCTS[1].id);
  });

  it('should get last page of repository', async () => {
    const pagination: TPagination = {
      currentPage: 4,
      maxItemsPerPage: 2
    }
    const useCase = new FindAllProductsUseCase(productRepository); 
    const {products, count} = await useCase.execute(pagination);

    expect(products.length).toBe(1);
    expect(count).toBe(7);
    expect(products[0].id).toBe(MEMORY_PRODUCTS[6].id);
  });
})
