package com.ak.shopkart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.shopkart.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}