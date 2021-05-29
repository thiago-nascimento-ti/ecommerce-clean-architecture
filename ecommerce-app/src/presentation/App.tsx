import * as React from "react";
import { Home, Cart, Product } from "./pages";
import Structure from "./components/Structure";
import { Router, routes } from "./pages/Router";

type Props = {}
const App: React.FC<Props> = () => {
  return (
    <Structure>
      <Router routes={[
        {path: routes.product(":id", ":name"), Component: Product},
        {path: routes.cart(), Component: Cart},
        {path: routes.home(), Component: Home},
      ]}/>
    </Structure>
  );
};

export default App;