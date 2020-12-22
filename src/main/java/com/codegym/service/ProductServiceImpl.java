package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAllByNameContaining(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> search(String keyword) {
        return null;
    }

    @Override
    public Page<Product> search(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product save(Product customer) {
        return null;
    }

    @Override
    public Iterable<Product> save(List<Product> customers) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public Iterable<Product> findAll(List<Long> ids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Product obj) {

    }

    @Override
    public void delete(List<Product> customers) {

    }

    @Override
    public void deleteAll() {

    }
}
