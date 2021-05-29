import { TItem, TProduct, TCreditCard } from "../entities";

export interface ICheckoutRepository {
  finalize: (creditCard: TCreditCard, items: Array<TItem>) => Promise<TCheckoutResult>;
}

export type TCheckoutResult = {
  status: CheckoutStatus
  itemsWithInsuficientStock: Array<TProduct>
}

export enum CheckoutStatus {
  InvalidCreditCard,
  InsuficientStock,
  Successful
}
