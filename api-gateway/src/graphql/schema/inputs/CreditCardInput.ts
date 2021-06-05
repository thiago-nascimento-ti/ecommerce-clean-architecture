import { gql } from 'apollo-server';

const CreditCardInput = gql`
  input CreditCardInput {
    cardNumber: String!
    name: String!
    validateDate: String!
    cvv: String!
    parcelAmount: Int!
  }
`;

export default CreditCardInput;