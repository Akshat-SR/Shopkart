package com.ak.shopkart.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.shopkart.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}