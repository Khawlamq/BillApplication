package com.assignment.billapplication.service;

import com.assignment.billapplication.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findByID(Long id);
    Product save(Product product);
    void deleteProduct(Long id);

}
