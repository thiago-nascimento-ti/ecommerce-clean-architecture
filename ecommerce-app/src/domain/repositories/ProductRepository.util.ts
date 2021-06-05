import { TProduct, } from '../entities';
import { IProductRepository, TProductList, TPagination } from '../repositories/ProductRepository';

export class FakeProductRepository implements IProductRepository {
  stock: number = 0;

  findAllProducts({currentPage, maxItemsPerPage}: TPagination): Promise<TProductList> {
    const first = (currentPage * maxItemsPerPage) - maxItemsPerPage;
    const last = currentPage * maxItemsPerPage;

    const productList: TProductList = {
      products: MEMORY_PRODUCTS.filter((item, index) => index >= first && index < last),
      count: MEMORY_PRODUCTS.length
    }
    return Promise.resolve(productList);
  }
  findProductById(id: number): Promise<TProduct> {
    const product: TProduct = MEMORY_PRODUCTS.find(product => product.id === id)!
    return Promise.resolve(product)
  }
  getProductStock(product: TProduct): Promise<number> {
    return Promise.resolve(this.stock);
  }
}

export const MEMORY_PRODUCTS = [
  {
    "id": 1,
    "categoryId": 1,
    "name": "Coca-Cola",
    "description": "Coca-Cola - Lata 350ml",
    "price": 3.5,
    "image": "https://i.imgur.com/LKbbq4W.png",
    "rate": 3,
    "maxParcels": 1
  },
  {
    "id": 2,
    "categoryId": 1,
    "name": "Fanta Laranja",
    "description": "Fanta Laranja - Lata 350ml",
    "price": 3.0,
    "image": "https://i.imgur.com/LKbbq4W.png",
    "rate": 3.5,
    "maxParcels": 1
  },
  {
    "id": 3,
    "categoryId": 2,
    "name": "Neugebauer Branco",
    "description": "Neugebauer Branco - 90g",
    "price": 4.35,
    "image": "https://i.imgur.com/LKbbq4W.png",
    "rate": 5,
    "maxParcels": 1
  },
  {
    "id": 4,
    "categoryId": 2,
    "name": "Laka",
    "description": "Laka - 90g",
    "price": 4.59,
    "image": "https://i.imgur.com/LKbbq4W.png",
    "rate": 2,
    "maxParcels": 1
  },
  {
    "id": 5,
    "categoryId": 3,
    "name": "Cheetos Onda Requeijão",
    "description": "Cheetos Onda Requeijão - 37g",
    "price": 2.5,
    "image": "https://i.imgur.com/LKbbq4W.png",
    "rate": 4.5,
    "maxParcels": 1
  },
  {
    "id": 6,
    "categoryId": 3,
    "name": "Cheetos Lua Parmesão",
    "description": "Cheetos Lua Parmesão - 37g",
    "price": 2.5,
    "image": "https://i.imgur.com/LKbbq4W.png",
    "rate": 1,
    "maxParcels": 1
  },
  {
    "id": 7,
    "categoryId": 1,
    "name": "Achocolatado Tirolzinho",
    "description": "Achocolatado Tirolzinho - 200ml",
    "price": 1.89,
    "image": "https://i.imgur.com/LKbbq4W.png",
    "rate": 5,
    "maxParcels": 1
  }
]