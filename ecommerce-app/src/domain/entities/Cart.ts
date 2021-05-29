import { IItem, TItem } from "./Item";
import { TProduct } from "./Product";

export interface ICart {
  items: Array<IItem>
  amount: number
  payable: number
}

export class TCart implements ICart {
  items: Array<TItem>
  amount: number
  payable: number

  constructor(items: Array<TItem> = [], amount: number = 0, payable: number = 0) {
    this.items = items;
    this.amount = amount;
    this.payable = payable;
  }

  add(product: TProduct, amount: number) {
    if (this.isProductExists(product)) {
      const item: TItem = this.findItemByProduct(product)!;
      item.add(amount);
    } else {
      this.items.push(new TItem(product, amount))
    }
    this.recalculate();
  }

  isProductExists(product: TProduct) {
    return this.items.some(item => item.isEqualsProduct(product))
  }

  subtract(product: TProduct, amount: number) {
    const item: TItem = this.findItemByProduct(product)!;
    item.subtract(amount);
    if (item.amount === 0) {
      this.remove(item);
    }
    this.recalculate();
  }

  private remove(item: TItem) {
    this.items = this.items
      .filter(({ product }) => !item.isEqualsProduct(product))
  }

  private findItemByProduct(product: TProduct): TItem | undefined {
    return this.items.find(
      item => item.isEqualsProduct(product)
    )
  }

  findItemAmount(product: TProduct): number {
    const item: TItem | undefined = this.items.find(
      item => item.isEqualsProduct(product)
    )
    return item ? item.amount : 0;
  }

  private recalculate() {
    this.payable = 0;
    this.amount = 0;
    this.items.forEach(item => {
      this.payable += item.product.price * item.amount;
      this.amount += item.amount;
    });
  }
}