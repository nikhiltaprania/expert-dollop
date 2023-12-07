package com.studentmanagement.service;

import com.studentmanagement.model.Enrollment;

import java.util.List;

public interface EnrollmentService {
    void enrollStudent(Enrollment enrollment);

    void withdrawStudent(int enrollmentId);

    Enrollment getEnrollmentById(int enrollmentId);
    void printAllDetailsOfEnrollment(int enrollmentId);

    List<Enrollment> getAllEnrollments();

    List<Enrollment> getEnrollmentsByStudentId(int studentId);

    List<Enrollment> getEnrollmentsByCourseId(int courseId);
}