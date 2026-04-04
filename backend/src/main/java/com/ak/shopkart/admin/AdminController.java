package com.ak.shopkart.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ak.shopkart.model.Product;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired
  private AdminService adminService;

  // Create product
  @PostMapping("/products")
  public Product createProduct(@RequestBody Product product) {
    return adminService.createProduct(product);
  }

  // Delete product
  @DeleteMapping("/products/{id}")
  public String deleteProduct(@PathVariable Long id) {
    return adminService.deleteProduct(id);
  }

  // Update order status
  @PutMapping("/orders/{orderId}/status")
  public String updateOrderStatus(
      @PathVariable Long orderId,
      @RequestParam String status) {

    return adminService.updateOrderStatus(orderId, status);
  }
}