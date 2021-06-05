import { TCreditCard, IItem } from "../../domain/entities";
import { TCheckoutResult } from "../../domain/repositories";
import { Factory } from "../../gateway/factory";
import { Resolver } from "./Resolver";

const checkoutRestRepository = Factory.createCheckoutRestRepository();

const schema = `
  checkout(creditCard: CreditCardInput, items: [ItemInput]): CheckoutResult
`; 

const mutation = {
  checkout: async (obj: any, { creditCard, items }: TCheckout): Promise<TCheckoutResult> => {
    return await checkoutRestRepository.finalize(creditCard, items);
  },
};

const resolver: Resolver = {
  mutations: {
    schema,
    mutation 
  },
  queries: null 
}
export default resolver

type TCheckout = {
  creditCard: TCreditCard
  items: Array<IItem>
}