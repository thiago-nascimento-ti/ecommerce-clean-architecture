package br.com.ecommerce.adapters.mapper;

public interface OutputMapper<T, E> {

  E fromEntity(T entity);
}
