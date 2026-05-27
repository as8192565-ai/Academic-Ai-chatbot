CREATE DATABASE academicbot;

USE academicbot;

CREATE TABLE students(

id INT PRIMARY KEY AUTO_INCREMENT,

name VARCHAR(100) NOT NULL,

email VARCHAR(100) UNIQUE NOT NULL,

department VARCHAR(100) NOT NULL,

password VARCHAR(100) NOT NULL,

confirmPassword VARCHAR(100) NOT NULL,

registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);





CREATE TABLE appointments(

id INT PRIMARY KEY AUTO_INCREMENT,

student_name VARCHAR(100),

email VARCHAR(100),

department VARCHAR(100),

advisor VARCHAR(100),

appointment_date DATE,

appointment_time VARCHAR(50),

reason VARCHAR(300),

booking_date TIMESTAMP
DEFAULT CURRENT_TIMESTAMP

);





CREATE TABLE enrolled_courses(

id INT PRIMARY KEY AUTO_INCREMENT,

student_name VARCHAR(100),

course_name VARCHAR(100),

enroll_date TIMESTAMP
DEFAULT CURRENT_TIMESTAMP

);