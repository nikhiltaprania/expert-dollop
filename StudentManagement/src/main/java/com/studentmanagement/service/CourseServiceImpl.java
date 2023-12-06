package com.studentmanagement.service;

import com.studentmanagement.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final Connection connection;

    public CourseServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addCourse(Course course) {
        String query = "INSERT INTO Course (course_name, course_code) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getCourseCode());

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println(e.getMessage());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void updateCourse(Course course) {
        String query = "UPDATE Course SET course_name=?, course_code=? WHERE course_id=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getCourseCode());
            statement.setInt(3, course.getCourseId());

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println(e.getMessage());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        String query = "DELETE FROM Course WHERE course_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setInt(1, courseId);

            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println(e.getMessage());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public Course getCourseById(int courseId) {
        String query = "SELECT * FROM Course WHERE course_id=?";
        Course course = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                course = mapResultSetToCourse(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        String query = "SELECT * FROM Course";
        List<Course> courses = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = mapResultSetToCourse(resultSet);
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return courses;
    }

    private Course mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setCourseId(resultSet.getInt("course_id"));
        course.setCourseName(resultSet.getString("course_name"));
        course.setCourseCode(resultSet.getString("course_code"));
        return course;
    }
}
