# expert-dollop

This project is a simple Student Management System designed to brush up on JDBC and MySQL core concepts.

## Overview

The Student Management System is a Java-based application that utilizes JDBC to interact with a MySQL database. It allows you to manage students, courses, enrollments, and addresses. The project is designed for educational purposes to strengthen understanding of JDBC and MySQL integration.

## Features

- **Student Management:** Add, update, delete, and retrieve student details.
- **Course Management:** Add, update, delete, and retrieve course details.
- **Enrollment Management:** Enroll and withdraw students from courses.
- **Address Management:** Add, update, delete, and retrieve address details.

## Technologies Used

- Java
- JDBC (Java Database Connectivity)
- MySQL
- Maven (for project build and dependencies)

## Project Structure

The project follows a standard Maven directory structure:

.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── yourcompany
│   │   │           └── studentmanagement
│   │   │               ├── dao
│   │   │               ├── model
│   │   │               ├── service
│   │   │               |── util
|   |   |               |-- StudentManagement.java
│   │   └── resources
├── target
├── pom.xml
├── README.md


- `dao`: Contains Data Access Object interfaces and implementations for database operations.
- `model`: Defines the data models for Student, Course, Enrollment, Address, etc.
- `service`: Service interfaces and implementations for business logic.
- `Main.java`: Main class to demonstrate the usage of the Student Management System.

## Usage

1. Clone the repository: `git clone <https://github.com/nikhiltaprania/expert-dollop.git>`
2. Build the project: `mvn clean install`
3. Run the application: `java -jar target/student-management-system.jar`

Make sure to configure your MySQL database connection details in the appropriate files.

## Database Schema

The project assumes the following database schema:

- `student`: Table for student details.
- `course`: Table for course details.
- `enrollment`: Table for enrollment details.
- `addresse`: Table for address details of students.

Adjust the schema based on your specific needs.

## License
Open Source Project