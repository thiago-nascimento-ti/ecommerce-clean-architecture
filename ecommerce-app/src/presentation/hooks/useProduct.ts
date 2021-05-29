import { useState, useEffect } from "react";
import { TProduct } from "../../domain/entities";
import { Factory } from "../../gateway/factory";

const productRepository = Factory.createProductRepository();
export default function useProducts(id: number): TProduct | null {
  const [product, setProduct] = useState<TProduct | null>(null);

  useEffect(() => {
    productRepository.findProductById(id).then(product => {
      setProduct(product);
    })
  }, [id]);

  return product;
}

