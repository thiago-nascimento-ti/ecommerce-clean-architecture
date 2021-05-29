import { TProduct } from "./Product";

export interface IItem {
  product: TProduct
  amount: number
}

export class TItem implements IItem {
  product: TProduct
  amount: number

  constructor(product: TProduct, amount: number = 0) {
    this.product = product;
    this.amount = amount;
  }

  add(amount: number) {
    this.amount += amount;
  }

  subtract(amount: number) {
    let newAmount = this.amount - amount;
    if (newAmount < 0) {
      throw new SubtractItemAmountError(this, amount);
    } 
    this.amount = newAmount;
  }

  isEqualsProduct({ id }: TProduct): boolean {
    return this.product.id === id;
  }

  toJSON(): IItem {
    return {
      product: this.product,
      amount: this.amount
    }
  }
}

export class SubtractItemAmountError extends Error {
  constructor(item: IItem, amount: number) {
    super(`Item ${item.product.name} does not have ${amount} to subtract`)
  }
}