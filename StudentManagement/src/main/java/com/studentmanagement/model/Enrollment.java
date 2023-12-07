package com.studentmanagement.model;

import java.sql.Date;

public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private Date enrollment_date;

    public Enrollment() {
    }

    public Enrollment(int studentId, int courseId, Date enrollment_date) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollment_date = enrollment_date;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollment_date() {
        return enrollment_date;
    }

    public void setEnrollment_date(Date enrollment_date) {
        this.enrollment_date = enrollment_date;
    }
    public void displayEnrollment() {
        System.out.format("\nEnrollment ID: %d, Student ID: %d, Course ID: %d, Enrollment Date: %tF%n",enrollmentId, studentId, courseId, enrollment_date);
    }
}