package com.example.demo.services;

import java.util.List;

public interface IAbstractService<T> {
    // TODO: 28.04.2022 подумать над типом возврата: какой полезнее будет 
    T create(T entity);
    T findById(Long id);
    T update(Long id, T entity);
    boolean delete(Long id);
    List<T> getAll();
}
