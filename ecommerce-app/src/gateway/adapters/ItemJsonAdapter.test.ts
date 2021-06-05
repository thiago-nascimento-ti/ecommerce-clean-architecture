import { IItem, TItem, TProduct } from "../../domain/entities";
import { ItemJsonAdapter } from "./ItemJsonAdapter";

describe('Item Json Adapter tests', () => {
  const product: TProduct = {id: 1, name: `teste`, description: "", price: 10, image: "", rate: 3, maxParcels: 1}
  
  it('should convert TItem to Json', () => {
    const item: TItem = new TItem(product, 1)
    const result: IItem = new ItemJsonAdapter().toJson(item);

    expect(result.product.id).toBe(item.product.id)
    expect(result.amount).toBe(item.amount)
    expect(result instanceof TItem).toBeFalsy()
  });

  it('should convert Json to TItem', () => {
    const item: IItem = {
      product,
      amount: 2
    };
    
    const result: TItem = new ItemJsonAdapter().fromJson(item);

    expect(result.product.id).toBe(item.product.id)
    expect(result.amount).toBe(item.amount)
    expect(result instanceof TItem).toBeTruthy()
  });
})