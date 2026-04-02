package com.ak.shopkart.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.shopkart.model.User;
import com.ak.shopkart.repo.UserRepo;
import com.ak.shopkart.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public User register(User user) {
    return userRepo.save(user);
  }

  @Override
  public User login(String email, String password) {
    User user = userRepo.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.getPassword().equals(password)) {
      throw new RuntimeException("Invalid password");
    }

    return user;
  }
}