package com.assignment.billapplication.repository;
import com.assignment.billapplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Long> {
}
