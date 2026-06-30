CREATE DATABASE ecommerce_db;

USE ecommerce_db;

CREATE TABLE products(
    product_id INT PRIMARY KEY,
    product_name VARCHAR(100),
    price DOUBLE,
    quantity INT
);