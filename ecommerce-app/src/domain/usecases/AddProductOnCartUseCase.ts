import { TCart, TProduct } from '../entities';
import { IProductRepository } from '../repositories';

export class AddProductOnCartUseCase {
  productRepository: IProductRepository;

  constructor(productRepository: IProductRepository) {
    this.productRepository = productRepository;
  }

  async execute(cart: TCart, product: TProduct, amount: number) {
    const stock = await this.productRepository.getProductStock(product);
    const itemAmount: number = cart.findItemAmount(product);
    const necessaryStock = itemAmount + amount;

    if (stock < necessaryStock) {
      throw new InsuficientItemStockError(product, necessaryStock)
    }
    cart.add(product, amount);
  }
}

export class InsuficientItemStockError extends Error {
  title: string
  content: string

  constructor(product: TProduct, necessaryStock: number) {
    super(`Product ${product.name} does not have ${necessaryStock} units in stock`)
    this.title = `Estoque insuficiente - ${product.name}`;
    this.content = "Desculpe mas o produto não possui estoque dispónivel para está quantidade";
  }
}