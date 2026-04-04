package com.ak.shopkart.service;

import java.util.List;

import com.ak.shopkart.dto.OrderDTO;

public interface OrderService {

  String placeOrder(Long addressId);

  List<OrderDTO> getUserOrders();

}