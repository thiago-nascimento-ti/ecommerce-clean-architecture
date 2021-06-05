import { gql } from "apollo-server";

const CheckoutResultType = gql`
  type CheckoutResult {
    status: String!
    itemsWithInsuficientStock: [Int]
  }
`;

export default CheckoutResultType;