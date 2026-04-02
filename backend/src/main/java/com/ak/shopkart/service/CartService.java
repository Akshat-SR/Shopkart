package com.ak.shopkart.service;

import com.ak.shopkart.dto.CartDTO;

public interface CartService {

  String addToCart(Long productId, int quantity);

  CartDTO getUserCart();

  String removeFromCart(Long productId);
}