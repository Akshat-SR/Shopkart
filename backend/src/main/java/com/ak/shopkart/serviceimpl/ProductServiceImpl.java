package com.ak.shopkart.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.shopkart.model.Product;
import com.ak.shopkart.repo.ProductRepo;
import com.ak.shopkart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepo productRepo;

  @Override
  public Product addProduct(Product product) {
    return productRepo.save(product);
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepo.findAll();
  }

  @Override
  public Product getProductById(Long id) {
    return productRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
  }

  @Override
  public Product updateProduct(Long id, Product product) {
    Product existing = getProductById(id);

    existing.setName(product.getName());
    existing.setDescription(product.getDescription());
    existing.setPrice(product.getPrice());
    existing.setQuantity(product.getQuantity());

    return productRepo.save(existing);
  }

  @Override
  public void deleteProduct(Long id) {
    productRepo.deleteById(id);
  }
}