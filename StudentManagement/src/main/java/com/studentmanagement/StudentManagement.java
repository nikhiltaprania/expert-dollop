package com.studentmanagement;

import com.studentmanagement.service.AddressService;
import com.studentmanagement.service.AddressServiceImpl;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.service.StudentServiceImpl;
import com.studentmanagement.util.ConnectionManager;
import com.studentmanagement.util.RunApp;

import java.sql.Connection;
import java.util.Scanner;

public class StudentManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection connection = ConnectionManager.getConnection();
        StudentService studentService = new StudentServiceImpl(connection);
        AddressService addressService = new AddressServiceImpl(connection);
        RunApp runApp = new RunApp(sc, studentService, addressService);
        runApp.manageStudent();
    }

}