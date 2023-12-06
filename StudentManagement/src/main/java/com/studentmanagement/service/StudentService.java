package com.studentmanagement.service;

import com.studentmanagement.model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int studentId);

    Student getStudentById(int studentId);

    void printStudentWithAddress(int studentId);

    List<Student> getAllStudents();
}