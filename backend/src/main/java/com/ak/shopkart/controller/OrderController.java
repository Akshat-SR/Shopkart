package com.ak.shopkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ak.shopkart.dto.OrderDTO;
import com.ak.shopkart.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping
  public String placeOrder(@RequestParam Long addressId) {
    return orderService.placeOrder(addressId);
  }

  @GetMapping
  public List<OrderDTO> getOrders() {
    return orderService.getUserOrders();
  }
}