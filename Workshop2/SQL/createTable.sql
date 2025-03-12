/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  trong
 * Created: Mar 12, 2025
 */

-- Tạo bảng tblUsers
CREATE TABLE tblUsers (
    username VARCHAR(50) PRIMARY KEY,
    [name] VARCHAR(100) NOT NULL,
    [password] VARCHAR(255) NOT NULL,
    [role] VARCHAR(20) NOT NULL CHECK ([role] IN ('Instructor', 'Student'))
);
GO

-- Tạo bảng tblExamCategories
CREATE TABLE tblExamCategories (
    category_id INT PRIMARY KEY IDENTITY(1,1),
    category_name VARCHAR(50) NOT NULL CHECK (category_name IN ('Quiz', 'Midterm', 'Final')),
    description TEXT
);
GO

-- Tạo bảng tblExams
CREATE TABLE tblExams (
    exam_id INT PRIMARY KEY IDENTITY(1,1),
    exam_title VARCHAR(100) NOT NULL,
    [subject] VARCHAR(50) NOT NULL,
    category_id INT FOREIGN KEY REFERENCES tblExamCategories(category_id),
    total_marks INT NOT NULL,
    Duration INT NOT NULL
);
GO

-- Tạo bảng tblQuestions
CREATE TABLE tblQuestions (
    question_id INT PRIMARY KEY IDENTITY(1,1),
    exam_id INT FOREIGN KEY REFERENCES tblExams(exam_id),
    question_text TEXT NOT NULL,
    option_a VARCHAR(100) NOT NULL,
    option_b VARCHAR(100) NOT NULL,
    option_c VARCHAR(100) NOT NULL,
    option_d VARCHAR(100) NOT NULL,
    correct_option CHAR(1) NOT NULL CHECK (correct_option IN ('A', 'B', 'C', 'D'))
);
GO
