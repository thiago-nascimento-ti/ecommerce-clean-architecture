import { TItem, TCreditCard } from "../../domain/entities"
import { ICheckoutRepository, TCheckoutResult } from "../../domain/repositories"
import { GraphQLClient, checkoutPM, TCheckoutVariables } from "../graphql";

export class CheckoutGraphQLRepository implements ICheckoutRepository {
  private graphQLClient: GraphQLClient

  constructor(graphQLClient: GraphQLClient) {
    this.graphQLClient = graphQLClient;
  }

  async finalize(creditCard: TCreditCard, items: Array<TItem>): Promise<TCheckoutResult> {
    const checkoutItems = items.map(({amount, product: {id, name, description, image, price} }) => ({
      amount,
      product: {id, name, description, image, price}
    }))
    const mutation = checkoutPM.get({creditCard, items: checkoutItems});
    return (await this.graphQLClient.execute<CheckoutResultData, TCheckoutVariables>(mutation)).checkout;
  }
}

type CheckoutResultData = {
  checkout: TCheckoutResult
}