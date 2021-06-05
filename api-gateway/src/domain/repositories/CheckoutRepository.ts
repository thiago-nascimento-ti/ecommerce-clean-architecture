import { IItem, TCreditCard } from "../entities";

export interface ICheckoutRepository {
  finalize: (creditCard: TCreditCard, items: Array<IItem>) => Promise<TCheckoutResult>;
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
