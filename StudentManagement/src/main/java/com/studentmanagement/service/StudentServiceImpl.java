package com.studentmanagement.service;

import com.studentmanagement.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final Connection connection;

    public StudentServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addStudent(Student student) {
        String query = "INSERT INTO Student (name, age, email, phone, address_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setInt(5, student.getAddress_id());

            statement.executeUpdate();
            connection.commit();
            System.out.println("Student Added Successfully");
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
    public void updateStudent(Student student) {
        String query = "UPDATE Student SET name=?, age=?, email=?, phone=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setInt(5, student.getStudentId());

            statement.executeUpdate();
            connection.commit();
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
    public void deleteStudent(int studentId) {
        String query = "DELETE FROM Student WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);

            statement.setInt(1, studentId);
            statement.executeUpdate();

            connection.commit();
            System.out.println("Student Removed Successfully");
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
    public Student getStudentById(int studentId) {
        String query = "SELECT * FROM Student WHERE id=?";
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                student = mapResultSetToStudent(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public void printStudentWithAddress(int studentId) {
        String query = "SELECT s.name, s.age, s.email, s.phone, a.city, a.state, a.pin_code FROM Student As s INNER JOIN Address AS a ON s.address_id = a.address_id WHERE s.id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String studentName = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int pinCode = resultSet.getInt("pin_code");

                System.out.println("\n++++Student Details:++++");
                System.out.println("Name: " + studentName);
                System.out.println("Age: " + age);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);

                System.out.println("++++Address Details:++++");
                System.out.println("City: " + city);
                System.out.println("State: " + state);
                System.out.println("Pin Code: " + pinCode);
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAllStudents() {
        String query = "SELECT * FROM Student";
        List<Student> students = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student student = mapResultSetToStudent(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    private Student mapResultSetToStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setStudentId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setAge(resultSet.getInt("age"));
        student.setEmail(resultSet.getString("email"));
        student.setPhone(resultSet.getString("phone"));
        student.setAddress_id(resultSet.getInt("address_id"));

        return student;
    }
}