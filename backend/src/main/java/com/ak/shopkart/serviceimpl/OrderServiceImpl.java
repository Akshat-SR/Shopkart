package com.ak.shopkart.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ak.shopkart.model.Cart;
import com.ak.shopkart.model.CartItem;
import com.ak.shopkart.model.Order;
import com.ak.shopkart.model.OrderItem;
import com.ak.shopkart.model.User;
import com.ak.shopkart.repo.CartRepo;
import com.ak.shopkart.repo.OrderRepo;
import com.ak.shopkart.repo.UserRepo;
import com.ak.shopkart.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private CartRepo cartRepo;

  @Autowired
  private OrderRepo orderRepo;

  @Autowired
  private UserRepo userRepo;

  @Override
  public String placeOrder() {

    String email = (String) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    User user = userRepo.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    Cart cart = cartRepo.findByUser(user)
        .orElseThrow(() -> new RuntimeException("Cart not found"));

    if (cart.getItems() == null || cart.getItems().isEmpty()) {
      throw new RuntimeException("Cannot place order: Cart is empty");
    }

    // Create order
    Order order = new Order();
    order.setUser(user);

    double total = 0;

    for (CartItem item : cart.getItems()) {

      OrderItem orderItem = new OrderItem();
      orderItem.setProduct(item.getProduct());
      orderItem.setQuantity(item.getQuantity());
      orderItem.setPrice(item.getProduct().getPrice());
      orderItem.setOrder(order);

      order.getItems().add(orderItem);

      total += item.getProduct().getPrice() * item.getQuantity();
    }

    order.setTotalPrice(total);

    orderRepo.save(order);

    // Clear cart
    cart.getItems().clear();
    cartRepo.save(cart);

    return "Order placed successfully!";
  }
}