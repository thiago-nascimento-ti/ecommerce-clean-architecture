export namespace HttpPost {
  export type Params = {
    uri: string;
    body?: any;
    headers?: {
      [key: string]: string;
    };
  };
}

export namespace HttpGet {
  export type Params = {
    uri: string;
    headers?: {
      [key: string]: string;
    };
  };
}

export interface HttpResponse<T = any>  {
  data: T;
  status: number;
  statusText: string;
  headers: any;
  request?: any;
}

export interface IHttpClient {
  post: <T = any>(params: HttpPost.Params) => Promise<HttpResponse<T>>;
  get: <T = any>(params: HttpGet.Params) => Promise<HttpResponse<T>>;
}