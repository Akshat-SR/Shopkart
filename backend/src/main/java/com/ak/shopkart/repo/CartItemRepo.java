package com.ak.shopkart.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.shopkart.model.Cart;
import com.ak.shopkart.model.CartItem;
import com.ak.shopkart.model.Product;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
  Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}