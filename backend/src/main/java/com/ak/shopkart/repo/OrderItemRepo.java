package com.ak.shopkart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.shopkart.model.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}