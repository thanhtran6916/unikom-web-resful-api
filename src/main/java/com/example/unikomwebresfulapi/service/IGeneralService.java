package com.example.unikomwebresfulapi.service;

import java.util.Optional;

public interface IGeneralService<T> {

    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    boolean delete(Long id);
}
