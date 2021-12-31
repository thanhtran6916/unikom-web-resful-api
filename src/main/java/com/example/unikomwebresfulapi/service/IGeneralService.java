package com.example.unikomwebresfulapi.service;

import org.springframework.data.domain.Page;

public interface IGeneralService<T, K> {

    Page<K> findAll();

    K findById(Long id);

    K save(T t);

    K edit(Long id, T t);

    void delete(Long id);
}
