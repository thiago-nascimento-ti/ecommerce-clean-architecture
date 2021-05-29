
import { TItem, TCreditCard } from "../entities";
import { ICheckoutRepository, TCheckoutResult, CheckoutStatus} from "./CheckoutRepository"

export class FakeCheckoutRepository implements ICheckoutRepository {
  checkoutResult: TCheckoutResult = {
    itemsWithInsuficientStock: [],
    status: CheckoutStatus.Successful
  }

  finalize(creditCard: TCreditCard, items: Array<TItem>): Promise<TCheckoutResult> {
    return Promise.resolve(this.checkoutResult)
  }
}