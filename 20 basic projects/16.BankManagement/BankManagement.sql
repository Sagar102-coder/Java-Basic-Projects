CREATE DATABASE bank_db;

USE bank_db;

CREATE TABLE accounts(
    account_no INT PRIMARY KEY,
    customer_name VARCHAR(100),
    account_type VARCHAR(50),
    balance DOUBLE
);