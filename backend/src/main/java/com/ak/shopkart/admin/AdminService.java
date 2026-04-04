package com.ak.shopkart.admin;

import com.ak.shopkart.model.Product;

public interface AdminService {

  Product createProduct(Product product);

  String deleteProduct(Long productId);

  String updateOrderStatus(Long orderId, String status);
}