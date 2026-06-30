CREATE DATABASE customer_db;

USE customer_db;

CREATE TABLE customers(
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(100),
    email VARCHAR(100),
    mobile VARCHAR(15),
    city VARCHAR(100)
);