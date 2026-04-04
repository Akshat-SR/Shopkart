package com.ak.shopkart.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ak.shopkart.model.User;
import com.ak.shopkart.model.UserRole;
import com.ak.shopkart.repo.UserRepo;
import com.ak.shopkart.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public User register(User user) {

    // Encrypt password before saving
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    // Set user role
    user.setRole(UserRole.ROLE_USER);

    return userRepo.save(user);
  }

  @Override
  public User login(String email, String password) {

    User user = userRepo.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    // Compare encrypted password
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("Invalid password");
    }

    return user;
  }
}