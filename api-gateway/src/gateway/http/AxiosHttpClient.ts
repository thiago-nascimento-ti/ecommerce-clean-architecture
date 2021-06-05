import axios, { AxiosResponse } from "axios";
import { IHttpClient, HttpPost, HttpGet } from "./HttpClient";

export class AxiosHttpClient implements IHttpClient {
  private host: string;

  constructor(host: string) {
    this.host = host;
  }

  async post<T = any>(params: HttpPost.Params): Promise<AxiosResponse<T>> {
    let axiosResponse: AxiosResponse;
    try {
      axiosResponse = await axios.post<T>(this.host + params.uri, params.body, {
        headers: params.headers,
      });
    } catch (error) {
      axiosResponse = error.response;
    }
    return axiosResponse;
  }

  async get<T = any>(params: HttpGet.Params): Promise<AxiosResponse<T>> {
    let axiosResponse: AxiosResponse;
    try {
      axiosResponse = await axios.get<T>(this.host + params.uri, {
        headers: params.headers,
      });
    } catch (error) {
      axiosResponse = error.response;
    }
    return axiosResponse;
  }
}