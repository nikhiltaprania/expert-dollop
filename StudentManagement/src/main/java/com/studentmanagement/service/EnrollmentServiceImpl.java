package com.studentmanagement.service;

import com.studentmanagement.model.Enrollment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {
    private final Connection connection;

    public EnrollmentServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void enrollStudent(Enrollment enrollment) {
        String query = "INSERT INTO Enrollment (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setInt(1, enrollment.getStudentId());
            statement.setInt(2, enrollment.getCourseId());
            statement.setDate(3, enrollment.getEnrollment_date());

            statement.executeUpdate();
            connection.commit();
            System.out.println("Enrollment Successful");
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void withdrawStudent(int enrollmentId) {
        String query = "DELETE FROM Enrollment WHERE enrollment_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setInt(1, enrollmentId);
            statement.executeUpdate();
            connection.commit();

            System.out.println("Enrollment has been withdrawn");
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) {
        String query = "SELECT * FROM Enrollment WHERE enrollment_id=?";
        Enrollment enrollment = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, enrollmentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                enrollment = mapResultSetToEnrollment(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollment;
    }

    @Override
    public void printAllDetailsOfEnrollment(int enrollmentId) {
        String query = "SELECT S.id, S.name, S.age, S.email, S.phone, C.course_name, C.course_code, A.city, A.state, A.pin_code from Enrollment as E INNER JOIN Student As S ON E.student_id = S.id INNER JOIN Course As C ON E.course_id = C.course_id INNER JOIN Address As A ON S.address_id = A.address_id WHERE E.enrollment_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, enrollmentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int studentId = resultSet.getInt("id");
                String studentName = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                String courseName = resultSet.getString("course_name");
                String courseCode = resultSet.getString("course_code");

                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int pinCode = resultSet.getInt("pin_code");

                System.out.println("\n+++++[Enrollment ID: " + enrollmentId + "]+++++");

                System.out.println("Student Details:");
                System.out.println("Student ID: " + studentId);
                System.out.println("Name: " + studentName);
                System.out.println("Age: " + age);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);

                System.out.println("Address Details:");
                System.out.println("City: " + city);
                System.out.println("State: " + state);
                System.out.println("Pin Code: " + pinCode);

                System.out.println("Applied For Course:");
                System.out.println("Course Name: " + courseName);
                System.out.println("Course Code: " + courseCode);
            } else {
                System.out.println("Enrollment with ID " + enrollmentId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        String query = "SELECT * FROM Enrollment";
        List<Enrollment> enrollments = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Enrollment enrollment = mapResultSetToEnrollment(resultSet);
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        String query = "SELECT * FROM Enrollment WHERE student_id=?";
        return getEnrollments(studentId, query);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(int courseId) {
        String query = "SELECT * FROM Enrollment WHERE course_id=?";
        return getEnrollments(courseId, query);
    }

    private List<Enrollment> getEnrollments(int id, String query) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Enrollment enrollment = mapResultSetToEnrollment(resultSet);
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    private Enrollment mapResultSetToEnrollment(ResultSet resultSet) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(resultSet.getInt("enrollment_id"));
        enrollment.setStudentId(resultSet.getInt("student_id"));
        enrollment.setCourseId(resultSet.getInt("course_id"));
        enrollment.setEnrollment_date(resultSet.getDate("enrollment_date"));
        return enrollment;
    }
}
