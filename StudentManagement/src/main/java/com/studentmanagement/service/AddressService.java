package com.studentmanagement.service;

import com.studentmanagement.model.Address;

import java.util.List;

public interface AddressService {
    void addAddress(Address address);

    void updateAddress(Address address);
    void deleteAddress(int addressId);
    Address getAddressById(int addressId);
    List<Address> getAllAddresses();
}