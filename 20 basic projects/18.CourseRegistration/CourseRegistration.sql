CREATE DATABASE course_db;

USE course_db;

CREATE TABLE courses(
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100),
    instructor VARCHAR(100)
);

CREATE TABLE registrations(
    reg_id INT PRIMARY KEY,
    student_name VARCHAR(100),
    course_id INT,
    FOREIGN KEY(course_id)
    REFERENCES courses(course_id)
);