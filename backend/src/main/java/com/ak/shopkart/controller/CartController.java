package com.ak.shopkart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ak.shopkart.dto.CartDTO;
import com.ak.shopkart.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

  @Autowired
  private CartService cartService;

  @PostMapping("/add")
  public String addToCart(@RequestParam Long productId,
      @RequestParam int quantity) {

    return cartService.addToCart(productId, quantity);
  }

  @GetMapping
  public CartDTO getCart() {
    return cartService.getUserCart();
  }

  @DeleteMapping("/remove")
  public String removeFromCart(@RequestParam Long productId) {
    return cartService.removeFromCart(productId);
  }
}