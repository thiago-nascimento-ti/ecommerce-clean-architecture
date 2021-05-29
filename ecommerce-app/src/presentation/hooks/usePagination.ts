import { useState } from "react";
import { TPagination } from "../../domain/repositories";

export default function usePagination(initialMaxItemsPerPage: number = 10): TPagination {
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [maxItemsPerPage, setMaxItemsPerPage] = useState<number>(
    initialMaxItemsPerPage
  );

  return {
    currentPage, 
    setCurrentPage, 
    maxItemsPerPage, 
    setMaxItemsPerPage
  };
}