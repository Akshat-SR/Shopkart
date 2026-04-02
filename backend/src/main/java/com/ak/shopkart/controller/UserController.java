package com.ak.shopkart.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ak.shopkart.jwt.JwtUtil;
import com.ak.shopkart.model.User;
import com.ak.shopkart.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/register")
  public User register(@RequestBody User user) {
    return userService.register(user);
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody User user) {

    User existingUser = userService.login(user.getEmail(), user.getPassword());

    String token = jwtUtil.generateToken(existingUser.getEmail());

    Map<String, String> response = new HashMap<>();
    response.put("token", token);

    return response;
  }

  @GetMapping("/profile")
  public String profile() {
    return "This is a protected profile endpoint";
  }
}