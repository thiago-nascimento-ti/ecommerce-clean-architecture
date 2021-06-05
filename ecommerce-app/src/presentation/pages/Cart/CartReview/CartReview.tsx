import React from "react";
import { ICart } from "../../../../domain/entities";
import useRoutes from "../../../hooks/useRoutes";
import CartReviewView from "./CartReviewView";
import { showErrorModal } from "../../../components/Message";

type Props = {
  cart: ICart
};
const CartReview: React.FC<Props> = ({ cart }) => {
  const routes = useRoutes();
  const goToHome = routes.home().go;
  const goToCheckout = () => {
    if (cart.amount === 0) {
      showErrorModal({
        title: "Sua cesta está vazia!", 
        content: "Não tem itens na sua cesta para finalizar o pagamento, selecione alguns produtos antes."
      }, routes.home().go);
    } else {
      routes.checkout().go()
    }
  }

  const maxParcels = cart.items.reduce(
    (max, {product:{maxParcels}}) => max < maxParcels ? maxParcels : max,
  1)

  return <CartReviewView {...{ cart, goToHome, goToCheckout, maxParcels }}/>
};

export default CartReview;