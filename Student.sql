CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;

CREATE TABLE IF NOT EXISTS admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO admins (username, password)
VALUES ('admin', 'admin123')
ON DUPLICATE KEY UPDATE username=username;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    roll_no VARCHAR(50) NOT NULL UNIQUE,
    department VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15),
    subject1 INT DEFAULT 0,
    subject2 INT DEFAULT 0,
    subject3 INT DEFAULT 0,
    total INT DEFAULT 0,
    grade CHAR(2) DEFAULT 'F'
);

INSERT INTO students (name, roll_no, department, email, phone, subject1, subject2, subject3, total, grade)
VALUES
('Gaurav Saini','R001','CSE','gaurav@example.com','9876543210',90,85,95,270,'A+'),
('Ansh Sharma','R002','ECE','ansh@example.com','9876543211',80,75,70,225,'B')
ON DUPLICATE KEY UPDATE roll_no=roll_no;