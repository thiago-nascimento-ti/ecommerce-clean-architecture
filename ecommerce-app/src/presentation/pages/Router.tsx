import React from "react";
import { Switch, Route, BrowserRouter } from "react-router-dom";
import Structure from "../components/Structure";

export const routes = {
  product: (id: string, name: string) => `/produto/${id}/${name}`,
  cart: () => "/carrinho",
  home: () => "/"
}

type Props = {
  routes: Array<TRoute>
}
export const Router: React.FC<Props> = ({ routes }) => {
  return (
    <BrowserRouter>
      <Structure>
        <Switch>
          {routes.map(({path, Component}, key) => {
            return <Route key={key} path={path} component={Component} />
          })}
        </Switch>
      </Structure>
    </BrowserRouter>
  );
};

export type TRoute = {
  path: string,
  Component: React.FC<any>
}