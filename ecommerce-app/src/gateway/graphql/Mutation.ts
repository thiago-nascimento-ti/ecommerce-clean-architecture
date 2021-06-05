import { TCreditCard } from "../../domain/entities"
import { PreparedMutation } from "./GraphQLClient"

export const checkoutPM = new PreparedMutation<TCheckoutVariables>(`
  mutation checkout($creditCard: CreditCardInput, $items: [ItemInput]) {
    checkout(creditCard: $creditCard, items: $items), {
      status,
      itemsWithInsuficientStock
    }
  }
`);

export type TCheckoutVariables = {
  creditCard: TCreditCard
  items: Array<TCheckoutItem>
}

export type TCheckoutItem = {
  amount: number
  product: TCheckoutProduct
}

export type TCheckoutProduct = {
  id: number
  name: string
  description: string
  price: number
  image: string
}