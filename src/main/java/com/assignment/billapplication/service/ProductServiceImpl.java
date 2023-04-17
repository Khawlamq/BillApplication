package com.assignment.billapplication.service;

import com.assignment.billapplication.entity.Product;
import com.assignment.billapplication.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductsRepository prodRep;
    @Override
    public List<Product> findAll() {
        return prodRep.findAll();
    }
    @Override
    public Product findByID(Long id) {
        return prodRep.findById(id).orElseThrow(() ->
                new RuntimeException("Sorry the customer with id "+ id + "is not found. "));
    }
    @Override
    public Product save(Product product) {
        return prodRep.save(product);
    }
    @Override
    public void deleteProduct(Long id) {
            prodRep.deleteById(id);
    }
}