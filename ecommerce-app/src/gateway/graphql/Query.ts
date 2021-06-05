import { PreparedQuery } from "./GraphQLClient"

export const findProductStockPQ = new PreparedQuery<TIdVariable>(`
  query product($id: Long) {
    product(id: $id) {
      stock
    }
  }
`);

export const findProductByIdPQ = new PreparedQuery<TIdVariable>(`
  query product($id: Long) {
    product(id: $id) {
      id,
      name,
      description,
      image,
      price,
      rate,
      maxParcels,
      stock
    }
  }
`);

export const findPagedProductsPQ = new PreparedQuery<TPagedVariables>(`
  query products($page: Int, $limit: Int) {
    products(page: $page, limit: $limit) {
      count,
      products {
        id,
        name,
        description,
        image,
        price,
        rate,
        maxParcels
      }
    }
  }
`);

export type TIdVariable = {
  id: number
}

export type TPagedVariables = {
  page: number
  limit: number
}
