package com.ak.shopkart.service;

import java.util.List;

import com.ak.shopkart.model.Product;

public interface ProductService {

  Product addProduct(Product product);

  List<Product> getAllProducts();

  Product getProductById(Long id);

  Product updateProduct(Long id, Product product);

  void deleteProduct(Long id);
}