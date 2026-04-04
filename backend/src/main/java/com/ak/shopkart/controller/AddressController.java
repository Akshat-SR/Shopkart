package com.ak.shopkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ak.shopkart.model.Address;
import com.ak.shopkart.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

  @Autowired
  private AddressService addressService;

  @PostMapping
  public Address addAddress(@RequestBody Address address) {
    return addressService.addAddress(address);
  }

  @GetMapping
  public List<Address> getAddresses() {
    return addressService.getUserAddresses();
  }
}
