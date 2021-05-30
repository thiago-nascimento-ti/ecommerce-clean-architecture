import * as React from "react";
import { Home, Cart, Product, Checkout } from "./pages";
import { Router } from "./pages/Router";
import useRoutes from "./hooks/useRoutes";
import { store } from './app/store';
import { Provider } from 'react-redux';

type Props = {}
const App: React.FC<Props> = () => {
  const routes = useRoutes();
  return (
    <Provider store={store}>
        <Router routes={[
          {path: routes.product(":id", ":name").location, Component: Product},
          {path: routes.cart().location, Component: Cart},
          {path: routes.checkout().location, Component: Checkout},
          {path: routes.home().location, Component: Home}
        ]}/>
    </Provider>
  );
};

export default App;