package com.studentmanagement.model;

public class Course {
    private int courseId;
    private String courseName;
    private String courseCode;

    public Course() {
    }

    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void displayCourse() {
        System.out.format("\nCourse ID: %d\nCourse Name: %s\nCourse Name: %s\n", courseId, courseName, courseCode);
    }
}
