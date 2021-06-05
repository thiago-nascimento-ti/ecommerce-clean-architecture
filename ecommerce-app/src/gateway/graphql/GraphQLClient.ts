import { IHttpClient, HttpPost } from "../http";

export class GraphQLClient {

  private httpClient: IHttpClient;

  constructor(httpClient: IHttpClient) {
    this.httpClient = httpClient;
  } 

  async execute<T, R>(query: Query<R>): Promise<T> {
    const params: HttpPost.Params = {
      uri: "",
      body: query
    };
    const { data } = (await this.httpClient.post<QueryData<T>>(params)).data;
    return data;
  }

}

export class PreparedQuery<T> {
  private query: string
  private operationName: Object | null

  constructor(query: string, operationName: Object | null = null) {
    this.query = query;
    this.operationName = operationName;
  }

  get(variables: T): Query<T> {
    return {
      query: this.query,
      operationName: this.operationName,
      variables
    }
  }
}

export interface Query<T> {
  operationName: Object | null,
  variables: T,
  query: string
}

export interface Mutation<T> extends Query<T> {}
export class PreparedMutation<T> extends PreparedQuery<T> {}

export type QueryData<T> = {
  data: T,
}