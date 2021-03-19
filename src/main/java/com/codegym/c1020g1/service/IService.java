package com.codegym.c1020g1.service;

public interface IService<T> {
    Iterable<T> findAll();

    T findById(Long id);

    T save(T t);

    void delete(Long id);
}
