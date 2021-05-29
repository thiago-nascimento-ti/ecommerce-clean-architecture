import * as React from "react";
import { Home, Cart, Product } from "./pages";
import Structure from "./components/Structure";
import { Router, routes } from "./pages/Router";
import { store } from './app/store';
import { Provider } from 'react-redux';

type Props = {}
const App: React.FC<Props> = () => {
  return (
    <Provider store={store}>
      <Structure>
        <Router routes={[
          {path: routes.product(":id", ":name"), Component: Product},
          {path: routes.cart(), Component: Cart},
          {path: routes.home(), Component: Home},
        ]}/>
      </Structure>
    </Provider>
  );
};

export default App;