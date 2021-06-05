import { gql } from 'apollo-server';

const ProductInput = gql`
  input ProductInput {
    id: Long!
    name: String!
    description: String!
    price: Float!
    image: String!
  }
`;

export default ProductInput;