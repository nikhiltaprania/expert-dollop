package com.studentmanagement.util;

import com.studentmanagement.model.Address;
import com.studentmanagement.model.Course;
import com.studentmanagement.model.Enrollment;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.AddressService;
import com.studentmanagement.service.CourseService;
import com.studentmanagement.service.EnrollmentService;
import com.studentmanagement.service.StudentService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class RunApp {
    private final Scanner sc;
    private final StudentService studentService;
    private final AddressService addressService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public RunApp(Scanner sc, StudentService studentService, AddressService addressService, CourseService courseService, EnrollmentService enrollmentService) {
        this.sc = sc;
        this.studentService = studentService;
        this.addressService = addressService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    public void manageStudent() {
        while (true) {
            System.out.println("\n==== Perform Student Operation ====");
            System.out.println("1. Add\n2. Update\n3. Delete");
            System.out.println("4. Get By ID\n5. Get All\n0. Back To Main Menu");
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
                    if (students.isEmpty()) {
                        System.out.println("No Students Found");
                    } else {
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

    public void manageCourse() {
        while (true) {
            System.out.println("\n==== Perform Course Operation ====");
            System.out.println("1. Add\n2. Update\n3. Delete");
            System.out.println("4. Get By ID\n5. Get All\n0. Back To Main Menu");
            System.out.print("Enter: ");

            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.println("Enter Course Details");
                    System.out.print("Name: ");
                    sc.nextLine();
                    String course_name = sc.nextLine();
                    System.out.print("Code: ");
                    String course_code = sc.next();
                    courseService.addCourse(new Course(course_name, course_code));
                }
                case 2 -> {
                    System.out.print("Enter Course ID: ");
                    int course_id = sc.nextInt();
                    Course course = courseService.getCourseById(course_id);

                    if (course != null) {
                        System.out.println("Enter New Course Details");
                        System.out.print("Name: ");
                        sc.nextLine();
                        String newName = sc.nextLine();
                        System.out.print("Code: ");
                        String newCode = sc.next();
                        Course c = new Course(newName, newCode);
                        c.setCourseId(course_id);

                        courseService.updateCourse(c);
                    } else {
                        System.out.println("Course Not Found With ID: " + course_id);
                    }
                }
                case 3 -> {
                    System.out.print("Enter Course ID: ");
                    int course_id = sc.nextInt();
                    Course course = courseService.getCourseById(course_id);

                    if (course != null) {
                        courseService.deleteCourse(course_id);
                    } else {
                        System.out.println("Course Not Found With ID: " + course_id);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Course ID: ");
                    int course_id = sc.nextInt();
                    Course course = courseService.getCourseById(course_id);

                    if (course != null) {
                        course.displayCourse();
                    } else {
                        System.out.println("Course Not Found With ID: " + course_id);
                    }
                }
                case 5 -> {
                    List<Course> courses = courseService.getAllCourses();

                    if (courses.isEmpty()) {
                        System.out.println("No Course Found");
                    } else {
                        System.out.println("\nAll Available Courses");
                        courses.forEach(Course::displayCourse);
                    }
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid Input ! Please Try Again...");
            }
        }
    }

    public void manageEnrollment() {
        while (true) {
            System.out.println("\n==== Perform Enrollment Operation ====");
            System.out.println("1. Add\n2. Withdraw\n3. Get By ID\n4. Get All");
            System.out.println("5. Get All By StudentId\n6. Get All By CourseId");
            System.out.print("0. Back To Main Menu\nEnter: ");

            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.println("Enter Required Details");
                    System.out.print("Student ID: ");
                    int studentId = sc.nextInt();
                    System.out.print("Course ID: ");
                    int courseId = sc.nextInt();
                    Date date = new Date(System.currentTimeMillis());

                    enrollmentService.enrollStudent(new Enrollment(studentId, courseId, date));
                }
                case 2 -> {
                    System.out.print("Enter Enrollment ID: ");
                    int enrollment_id = sc.nextInt();
                    Enrollment enrollment = enrollmentService.getEnrollmentById(enrollment_id);

                    if (enrollment != null) {
                        enrollmentService.withdrawStudent(enrollment_id);
                    } else {
                        System.out.println("No enrollment registered with ID: " + enrollment_id);
                    }
                }
                case 3 -> {
                    System.out.print("Enter Enrollment ID: ");
                    int enrollment_id = sc.nextInt();
                    Enrollment enrollment = enrollmentService.getEnrollmentById(enrollment_id);

                    if (enrollment != null) {
                        enrollmentService.printAllDetailsOfEnrollment(enrollment_id);
                    } else {
                        System.out.println("No enrollment registered with ID: " + enrollment_id);
                    }
                }
                case 4 -> {
                    List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
                    if (enrollments.isEmpty()) {
                        System.out.println("No Enrollments Yet");
                    } else {
                        viewEnrollments(enrollments);
                    }
                }
                case 5 -> {
                    System.out.print("Enter Student ID: ");
                    int student_id = sc.nextInt();
                    List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(student_id);
                    if (enrollments.isEmpty()) {
                        System.out.println("No Enrollments Yet");
                    } else {
                        viewEnrollments(enrollments);
                    }
                }
                case 6 -> {
                    System.out.print("Enter Course ID: ");
                    int course_id = sc.nextInt();
                    List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(course_id);
                    if (enrollments.isEmpty()) {
                        System.out.println("No Enrollments Yet");
                    } else {
                        viewEnrollments(enrollments);
                    }
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid Input ! Please Try Again...");
            }
        }
    }

    private void viewEnrollments(List<Enrollment> enrollments) {
        while (true) {
            System.out.println("\n1. View All Enrollments\n2. View Enrollments With Full Details");
            System.out.print("0. Back To Previous Menu\nEnter: ");
            switch (sc.nextInt()) {
                case 1 -> enrollments.forEach(Enrollment::displayEnrollment);
                case 2 ->
                        enrollments.forEach(enrollment -> enrollmentService.printAllDetailsOfEnrollment(enrollment.getEnrollmentId()));
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid Input ! Please Try Again...");
            }
        }
    }
}