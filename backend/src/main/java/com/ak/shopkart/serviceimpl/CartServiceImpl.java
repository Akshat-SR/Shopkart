package com.ak.shopkart.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ak.shopkart.dto.CartDTO;
import com.ak.shopkart.mapper.CartMapper;
import com.ak.shopkart.model.Cart;
import com.ak.shopkart.model.CartItem;
import com.ak.shopkart.model.Product;
import com.ak.shopkart.model.User;
import com.ak.shopkart.repo.CartItemRepo;
import com.ak.shopkart.repo.CartRepo;
import com.ak.shopkart.repo.ProductRepo;
import com.ak.shopkart.repo.UserRepo;
import com.ak.shopkart.service.CartService;

@Service
public class CartServiceImpl implements CartService {

  @Autowired
  private CartRepo cartRepo;

  @Autowired
  private CartItemRepo cartItemRepo;

  @Autowired
  private ProductRepo productRepo;

  @Autowired
  private UserRepo userRepo;

  @Override
  public String addToCart(Long productId, int quantity) {

    // Get logged-in user from JWT
    User user = (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    // Get or create cart
    Cart cart = cartRepo.findByUser(user).orElseGet(() -> {
      Cart newCart = new Cart();
      newCart.setUser(user);
      return cartRepo.save(newCart);
    });

    // Get product
    Product product = productRepo.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    // Create cart item
    Optional<CartItem> existingItem = cartItemRepo.findByCartAndProduct(cart, product);

    if (existingItem.isPresent()) {
      // Update quantity
      CartItem item = existingItem.get();
      item.setQuantity(item.getQuantity() + quantity);
      cartItemRepo.save(item);
    } else {
      // Create new item
      CartItem item = new CartItem();
      item.setProduct(product);
      item.setQuantity(quantity);
      item.setCart(cart);

      cartItemRepo.save(item);
    }

    return "Product added to cart";
  }

  @Override
  public CartDTO getUserCart() {

    User user = (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    Cart cart = cartRepo.findByUser(user)
        .orElseThrow(() -> new RuntimeException("Cart is empty"));

    return CartMapper.toDTO(cart);
  }

  @Override
  public String removeFromCart(Long productId) {

    User user = (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    Cart cart = cartRepo.findByUser(user)
        .orElseThrow(() -> new RuntimeException("Cart not found"));

    Product product = productRepo.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    CartItem item = cartItemRepo.findByCartAndProduct(cart, product)
        .orElseThrow(() -> new RuntimeException("Item not in cart"));

    cart.getItems().remove(item); // remove from memory
    cartRepo.save(cart);

    return "Item removed from cart";
  }
}