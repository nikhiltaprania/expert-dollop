package com.studentmanagement;

import com.studentmanagement.service.*;
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
        CourseService courseService = new CourseServiceImpl(connection);
        EnrollmentService enrollmentService = new EnrollmentServiceImpl(connection);
        RunApp runApp = new RunApp(sc, studentService, addressService, courseService, enrollmentService);


        while (true) {
            System.out.println("\nPerform Operation On");
            System.out.println("1. Students\n2. Courses\n3. Enrollments");
            System.out.print("0. Exist App\nEnter: ");
            switch (sc.nextInt()) {
                case 1 -> runApp.manageStudent();
                case 2 -> runApp.manageCourse();
                case 3 -> runApp.manageEnrollment();
                case 0 -> {
                    sc.close();
                    ConnectionManager.closeConnection(connection);
                    System.out.println("Existing App...");
                    return;
                }
                default -> System.out.println("Invalid Input ! Try Again...");
            }
        }
    }

}