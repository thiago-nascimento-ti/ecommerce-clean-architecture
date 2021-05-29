import { IItem, TItem } from "../../domain/entities";

export class ItemJsonAdapter {
  fromJson(item :IItem): TItem {
    return new TItem(item.product, item.amount); 
  }
 
  toJson(item: TItem): IItem {
    const { product, amount } = item;
    return { product, amount };
  }
}

