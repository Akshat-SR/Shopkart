package com.ak.shopkart.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ak.shopkart.model.Address;
import com.ak.shopkart.model.User;
import com.ak.shopkart.repo.AddressRepo;
import com.ak.shopkart.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  private AddressRepo addressRepo;

  @Override
  public Address addAddress(Address address) {

    User user = (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    address.setUser(user);

    return addressRepo.save(address);
  }

  @Override
  public List<Address> getUserAddresses() {

    User user = (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    return addressRepo.findByUser(user);
  }
}