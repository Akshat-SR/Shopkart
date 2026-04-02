package com.ak.shopkart.service;

import com.ak.shopkart.model.User;

public interface UserService {
  User register(User user);

  User login(String email, String password);
}