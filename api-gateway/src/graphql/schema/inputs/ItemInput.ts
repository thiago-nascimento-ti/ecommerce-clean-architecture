import { gql } from "apollo-server";

const ItemInput = gql`
  input ItemInput {
    amount: Int!
    product: ProductInput
  }
`;

export default ItemInput;