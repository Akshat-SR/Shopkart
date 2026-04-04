package com.ak.shopkart.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.shopkart.model.Address;
import com.ak.shopkart.model.User;

public interface AddressRepo extends JpaRepository<Address, Long> {
  List<Address> findByUser(User user);
}