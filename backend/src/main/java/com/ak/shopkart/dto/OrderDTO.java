package com.ak.shopkart.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

  private Long orderId;
  private double totalPrice;
  private List<OrderItemDTO> items;
  private String status;
}