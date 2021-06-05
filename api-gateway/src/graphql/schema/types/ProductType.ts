import { gql } from 'apollo-server';

const ProductType = gql`
  type Product {
    id: Long!
    name: String!
    description: String!
    price: Float!
    image: String!
    rate: Float!
    maxParcels: Int!
    stock: Int!
  }
`;

export default ProductType;