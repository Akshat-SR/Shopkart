package com.ak.shopkart.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.shopkart.model.Cart;
import com.ak.shopkart.model.User;

public interface CartRepo extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUser(User user);
}