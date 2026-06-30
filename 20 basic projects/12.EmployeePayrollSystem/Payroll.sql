CREATE DATABASE payroll_db;

USE payroll_db;
CREATE TABLE employees(
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(100),
    department VARCHAR(100),
    salary DOUBLE
);