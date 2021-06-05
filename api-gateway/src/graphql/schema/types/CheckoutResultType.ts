import { gql } from "apollo-server";

const CheckoutResultType = gql`
  type CheckoutResult {
    status: String!
    itemsWithInsuficientStock: [Long]
  }
`;

export default CheckoutResultType;