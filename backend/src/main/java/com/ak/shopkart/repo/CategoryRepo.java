package com.ak.shopkart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.shopkart.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}