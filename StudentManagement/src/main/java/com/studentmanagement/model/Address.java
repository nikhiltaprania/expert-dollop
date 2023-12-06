package com.studentmanagement.model;

import java.io.Serializable;

public class Address {
    private int address_id;
    private String city;
    private String state;
    private int pinCode;

    public Address() {
    }

    public Address(String city, String state, int pinCode) {
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public void displayAddress() {
        System.out.format("Address: %d, %s, %s, %d\n", address_id, city, state, pinCode);
    }
}
