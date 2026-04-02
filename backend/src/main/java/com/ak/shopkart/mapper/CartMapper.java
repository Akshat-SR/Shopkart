package com.ak.shopkart.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ak.shopkart.dto.CartDTO;
import com.ak.shopkart.dto.CartItemDTO;
import com.ak.shopkart.model.Cart;
import com.ak.shopkart.model.CartItem;

public class CartMapper {

  public static CartDTO toDTO(Cart cart) {

    List<CartItemDTO> itemDTOList = new ArrayList<>();
    double total = 0;

    for (CartItem item : cart.getItems()) {

      CartItemDTO dto = new CartItemDTO();
      dto.setProductName(item.getProduct().getName());
      dto.setPrice(item.getProduct().getPrice());
      dto.setQuantity(item.getQuantity());

      itemDTOList.add(dto);

      total += item.getProduct().getPrice() * item.getQuantity();
    }

    CartDTO cartDTO = new CartDTO();
    cartDTO.setItems(itemDTOList);
    cartDTO.setTotalPrice(total);

    return cartDTO;
  }
}