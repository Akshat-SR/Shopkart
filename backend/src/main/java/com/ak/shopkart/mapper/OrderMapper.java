package com.ak.shopkart.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ak.shopkart.dto.OrderDTO;
import com.ak.shopkart.dto.OrderItemDTO;
import com.ak.shopkart.model.Order;
import com.ak.shopkart.model.OrderItem;

public class OrderMapper {

  public static OrderDTO toDTO(Order order) {

    List<OrderItemDTO> itemDTOList = new ArrayList<>();

    for (OrderItem item : order.getItems()) {

      OrderItemDTO dto = new OrderItemDTO();
      dto.setProductName(item.getProduct().getName());
      dto.setPrice(item.getPrice());
      dto.setQuantity(item.getQuantity());

      itemDTOList.add(dto);
    }

    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setOrderId(order.getId());
    orderDTO.setTotalPrice(order.getTotalPrice());
    orderDTO.setItems(itemDTOList);
    orderDTO.setStatus(order.getStatus().name());

    return orderDTO;
  }

  public static List<OrderDTO> toDTOList(List<Order> orders) {

    List<OrderDTO> list = new ArrayList<>();

    for (Order order : orders) {
      list.add(toDTO(order));
    }

    return list;
  }
}