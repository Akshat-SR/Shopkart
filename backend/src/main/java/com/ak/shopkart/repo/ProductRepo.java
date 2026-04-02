package com.ak.shopkart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.shopkart.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}