import { TItem, TCreditCard } from "../entities";

export interface ICheckoutRepository {
  finalize: (creditCard: TCreditCard, items: Array<TItem>) => Promise<TCheckoutResult>;
}

export type TCheckoutResult = {
  status: CheckoutStatus
  itemsWithInsuficientStock: Array<Number>
}

export enum CheckoutStatus {
  InvalidCreditCard = "InvalidCreditCard",
  InsuficientStock = "InsuficientStock",
  Successful = "Successful"
}
