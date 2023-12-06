package com.studentmanagement.model;

public class Student {
    private int studentId;
    private String name;
    private int age;
    private String email;
    private String phone;
    private int address_id;

    public Student() {
    }

    public Student(String name, int age, String email, String phone) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public void displayStudent() {
        System.out.format("Name: %s\nAge: %d\nEmail: %s\nPhone No: %s\nAddress ID: %d\n", name, age, email, phone, address_id);
    }
}
