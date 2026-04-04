package com.ak.shopkart.service;

import java.util.List;

import com.ak.shopkart.model.Address;

public interface AddressService {

  Address addAddress(Address address);

  List<Address> getUserAddresses();
}