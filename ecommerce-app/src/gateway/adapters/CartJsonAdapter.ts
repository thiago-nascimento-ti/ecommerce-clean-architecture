import { ICart, TCart, TItem } from "../../domain/entities";
import { ItemJsonAdapter } from "./ItemJsonAdapter";

export class CartJsonAdapter {
  itemAdapter: ItemJsonAdapter = new ItemJsonAdapter();

  fromJson(cart :ICart): TCart {
    const items: Array<TItem> = cart.items
      .map(item => this.itemAdapter.fromJson(item));
    return new TCart(items, cart.amount, cart.payable); 
  }
 
  toJson(cart: TCart): ICart {
    const { amount, payable } = cart;
    const items = cart.items
      .map(item => this.itemAdapter.toJson(item));
    return { amount, payable, items};
  }
}