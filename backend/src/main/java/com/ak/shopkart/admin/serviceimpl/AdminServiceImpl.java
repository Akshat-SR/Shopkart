package com.ak.shopkart.admin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.shopkart.admin.AdminService;
import com.ak.shopkart.model.Order;
import com.ak.shopkart.model.OrderStatus;
import com.ak.shopkart.model.Product;
import com.ak.shopkart.repo.OrderRepo;
import com.ak.shopkart.repo.ProductRepo;

@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  private ProductRepo productRepo;

  @Autowired
  private OrderRepo orderRepo;

  @Override
  public Product createProduct(Product product) {
    return productRepo.save(product);
  }

  @Override
  public String deleteProduct(Long productId) {

    Product product = productRepo.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    productRepo.delete(product);

    return "Product deleted successfully";
  }

  @Override
  public String updateOrderStatus(Long orderId, String status) {

    Order order = orderRepo.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found"));

    order.setStatus(OrderStatus.valueOf(status.toUpperCase()));

    orderRepo.save(order);

    return "Order status updated to " + status;
  }
}