CREATE DATABASE exam_db;

USE exam_db;

CREATE TABLE questions(
    question_id INT PRIMARY KEY,
    question VARCHAR(255),
    option1 VARCHAR(100),
    option2 VARCHAR(100),
    option3 VARCHAR(100),
    option4 VARCHAR(100),
    correct_answer INT
);