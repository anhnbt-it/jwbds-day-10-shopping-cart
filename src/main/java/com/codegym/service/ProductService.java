package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> findAllByNameContaining(String keyword, Pageable pageable);

    Iterable<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    List<Product> search(String keyword);

    Page<Product> search(String keyword, Pageable pageable);

    Product findOne(Long id);

    Product save(Product customer);

    Iterable<Product> save(List<Product> customers);

    boolean exists(Long id);

    Iterable<Product> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(Product obj);

    void delete(List<Product> customers);

    void deleteAll();
}
