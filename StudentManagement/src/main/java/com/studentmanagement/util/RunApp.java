package com.studentmanagement.util;

import com.studentmanagement.model.Address;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.AddressService;
import com.studentmanagement.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class RunApp {
    private final Scanner sc;
    private final StudentService studentService;
    private final AddressService addressService;

    public RunApp(Scanner sc, StudentService studentService, AddressService addressService) {
        this.sc = sc;
        this.studentService = studentService;
        this.addressService = addressService;
    }

    public void manageStudent() {
        while (true) {
            System.out.println("\n==== Perform Student Operation ====");
            System.out.println("1. Add\n2. Update\n3. Delete");
            System.out.println("4. Get By ID\n5. Gel All");
            System.out.print("Enter: ");

            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.println("\nEnter Student Details");
                    Student student = EnterStudentDetails();

                    System.out.println("Enter Student Address Details");
                    System.out.print("Address ID: ");
                    int address_id = sc.nextInt();

                    Address address = EnterAddressDetails();
                    address.setAddress_id(address_id);
                    student.setAddress_id(address_id);

                    addressService.addAddress(address);
                    studentService.addStudent(student);
                }
                case 2 -> {
                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();
                    Student student = studentService.getStudentById(studentId);

                    if (student != null) {
                        UpdateStudent(student);
                    } else {
                        System.out.println("No Student Found With ID: " + studentId);
                    }
                }
                case 3 -> {
                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();
                    Student student = studentService.getStudentById(studentId);

                    if (student != null) {
                        studentService.deleteStudent(studentId);
                        addressService.deleteAddress(student.getAddress_id());
                    } else {
                        System.out.println("No Student Found With ID: " + studentId);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();
                    Student student = studentService.getStudentById(studentId);

                    if (student != null) {
                        studentService.printStudentWithAddress(studentId);
                    } else {
                        System.out.println("Student with ID " + studentId + " not found.");
                    }
                }
                case 5 -> {
                    List<Student> students = studentService.getAllStudents();
                    if(students.isEmpty()) {
                        System.out.println("No Students Found");
                    }else {
                        students.forEach(student -> studentService.printStudentWithAddress(student.getStudentId()));
                    }
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid Input ! Please Try Again...");
            }
        }
    }

    private void UpdateStudent(Student student) {
        while (true) {
            System.out.println("\nUpdate Student Details");
            System.out.println("1. Academic Details Only");
            System.out.println("2. Address Only");
            System.out.print("3. Both\n0. Back To Previous Menu\nEnter: ");

            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.println("\nEnter Student's New Details");
                    Student s = EnterStudentDetails();
                    s.setStudentId(student.getStudentId());
                    s.setAddress_id(student.getAddress_id());
                    studentService.updateStudent(s);
                }
                case 2 -> {
                    System.out.println("\nEnter Student's New Address");
                    Address address = EnterAddressDetails();
                    address.setAddress_id(student.getAddress_id());
                    addressService.updateAddress(address);
                }
                case 3 -> {
                    System.out.println("\nEnter Student's New Details");
                    Student s = EnterStudentDetails();
                    s.setStudentId(student.getStudentId());
                    s.setAddress_id(student.getAddress_id());

                    System.out.println("\nEnter Student's New Address");
                    Address a = EnterAddressDetails();
                    a.setAddress_id(student.getAddress_id());

                    addressService.updateAddress(a);
                    studentService.updateStudent(s);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid Input ! Please Try Again...");
            }
        }
    }

    private Student EnterStudentDetails() {
        System.out.print("Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        System.out.print("Email: ");
        String email = sc.next();
        System.out.print("Phone Number: ");
        String phone = sc.next();
        return new Student(name, age, email, phone);
    }

    private Address EnterAddressDetails() {
        System.out.print("City: ");
        sc.nextLine();
        String city = sc.nextLine();
        System.out.print("State: ");
        String state = sc.nextLine();
        System.out.print("Pin Code: ");
        int pinCode = sc.nextInt();
        return new Address(city, state, pinCode);
    }
}