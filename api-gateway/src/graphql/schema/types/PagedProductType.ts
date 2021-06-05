import { gql } from "apollo-server";

const PagedProductType = gql`
  type PagedProduct {
    count: Int!
    products: [Product]
  }
`;

export default PagedProductType;