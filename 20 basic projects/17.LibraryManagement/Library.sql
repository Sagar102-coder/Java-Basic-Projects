CREATE DATABASE library_db;

USE library_db;

CREATE TABLE books(
    book_id INT PRIMARY KEY,
    book_name VARCHAR(150),
    author VARCHAR(100),
    available BOOLEAN
);