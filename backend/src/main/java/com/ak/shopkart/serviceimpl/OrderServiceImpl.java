package com.ak.shopkart.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ak.shopkart.dto.OrderDTO;
import com.ak.shopkart.mapper.OrderMapper;
import com.ak.shopkart.model.Address;
import com.ak.shopkart.model.Cart;
import com.ak.shopkart.model.CartItem;
import com.ak.shopkart.model.Order;
import com.ak.shopkart.model.OrderItem;
import com.ak.shopkart.model.User;
import com.ak.shopkart.repo.AddressRepo;
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

  @Autowired
  private AddressRepo addressRepo;

  @Override
  public String placeOrder(Long addressId) {

    User user = (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    Cart cart = cartRepo.findByUser(user)
        .orElseThrow(() -> new RuntimeException("Cart not found"));

    if (cart.getItems().isEmpty()) {
      throw new RuntimeException("Cart is empty");
    }

    Address address = addressRepo.findById(addressId)
        .orElseThrow(() -> new RuntimeException("Address not found"));

    Order order = new Order();
    order.setUser(user);
    order.setAddress(address);

    double total = 0;

    List<OrderItem> orderItems = new ArrayList<>();

    for (CartItem cartItem : cart.getItems()) {

      OrderItem item = new OrderItem();
      item.setProduct(cartItem.getProduct());
      item.setQuantity(cartItem.getQuantity());
      item.setPrice(cartItem.getProduct().getPrice());
      item.setOrder(order);

      total += cartItem.getQuantity() * cartItem.getProduct().getPrice();

      orderItems.add(item);
    }

    order.setItems(orderItems);
    order.setTotalPrice(total);

    orderRepo.save(order);

    // clear cart
    cart.getItems().clear();
    cartRepo.save(cart);

    return "Order placed successfully";
  }

  @Override
  public List<OrderDTO> getUserOrders() {

    User user = (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    List<Order> orders = orderRepo.findByUser(user);

    return OrderMapper.toDTOList(orders);
  }
}