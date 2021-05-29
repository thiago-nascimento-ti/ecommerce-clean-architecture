import { useHistory } from 'react-router-dom';

export default function useRoutes(): TRoutes {
  const history = useHistory();

  const createRoute = (location: string): TRoute => {
    const go = () => {
      history.push(location);
    }
    return { location, go };
  }

  return {
    product: (id: string, name: string): TRoute => createRoute(`/produto/${id}/${name}`),
    cart: (): TRoute => createRoute("/carrinho"),
    home: (): TRoute => createRoute("/")
  }
}

export type TRoutes = {
  product: (id: string, name: string) => TRoute
  cart: () => TRoute
  home: () => TRoute
}

export type TRoute = {
  location: string
  go: () => void
}

export type TLocation = {
  name: string
  uri: string
}