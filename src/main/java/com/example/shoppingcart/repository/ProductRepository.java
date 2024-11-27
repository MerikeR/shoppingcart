package com.example.shoppingcart.repository;

import com.example.shoppingcart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndPrice(String name, double price);
    void deleteByName(String name);
    void deleteByPrice(double price);
}