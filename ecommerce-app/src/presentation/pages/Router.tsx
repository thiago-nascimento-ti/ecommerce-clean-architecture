import React from "react";
import { Switch, Route, BrowserRouter } from "react-router-dom";
import Structure from "../components/Structure";

type Props = {
  routes: Array<TComponentRoute>
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

export type TComponentRoute = {
  path: string,
  Component: React.FC<any>
}