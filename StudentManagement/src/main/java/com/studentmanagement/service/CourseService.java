package com.studentmanagement.service;

import com.studentmanagement.model.Course;

import java.util.List;

public interface CourseService {
    void addCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(int courseId);

    Course getCourseById(int courseId);

    List<Course> getAllCourses();
}