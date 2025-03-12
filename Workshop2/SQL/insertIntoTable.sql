/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  trong
 * Created: Mar 12, 2025
 */

INSERT INTO tblUsers (username, Name, password, Role)
VALUES 
('john_doe', 'John Doe', 'password123', 'Instructor'),
('jane_smith', 'Jane Smith', 'password456', 'Student'),
('alice_wang', 'Alice Wang', 'password789', 'Student'),
('bob_jones', 'Bob Jones', 'password101', 'Instructor'),
('sarah_lee', 'Sarah Lee', 'password202', 'Student'),
('mike_brown', 'Mike Brown', 'password303', 'Student');

INSERT INTO tblExamCategories (category_name, description)
VALUES 
('Quiz', 'A short assessment to test knowledge on a specific topic.'),
('Midterm', 'An exam taken halfway through the course to assess progress.'),
('Final', 'A comprehensive exam at the end of the course.'),
('Quiz', 'A quick test to evaluate understanding of recent lessons.'),
('Midterm', 'An assessment covering the first half of the course material.'),
('Final', 'A comprehensive evaluation of all course content.');





INSERT INTO tblExams (exam_title, Subject, category_id, total_marks, Duration)
VALUES 
('C Programming Quiz', 'C Programming', 13, 20, 30), -- category_id = 13 (Quiz)
('C++ Midterm Exam', 'C++ Programming', 14, 50, 60), -- category_id = 14 (Midterm)
('Java Final Exam', 'Java Programming', 15, 100, 120), -- category_id = 15 (Final)
('Python Quiz', 'Python Programming', 16, 25, 30), -- category_id = 16 (Quiz)
('Data Structures Midterm', 'Data Structures', 17, 60, 90), -- category_id = 17 (Midterm)
('Algorithms Final', 'Algorithms', 18, 100, 120); -- category_id = 18 (Final)



INSERT INTO tblQuestions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option)
VALUES 
-- Câu hỏi cho exam_id = 3 (C Programming Quiz)
(3, 'What is the output of printf("%d", 10); in C?', '10', '0', 'Error', 'None', 'A'),
(3, 'Which keyword is used to define a constant in C?', 'const', 'final', 'static', 'define', 'A'),
(3, 'What is the size of an integer in C?', '2 bytes', '4 bytes', '8 bytes', 'Depends on the system', 'D'),

-- Câu hỏi cho exam_id = 4 (C++ Midterm Exam)
(4, 'Which operator is used for dynamic memory allocation in C++?', 'new', 'malloc', 'alloc', 'create', 'A'),
(4, 'What is the default access specifier for members of a class in C++?', 'private', 'public', 'protected', 'none', 'A'),
(4, 'Which of the following is a correct way to declare a reference in C++?', 'int &x;', 'int x&;', 'int & x;', 'int x;', 'A'),

-- Câu hỏi cho exam_id = 5 (Java Final Exam)
(5, 'Which of the following is not a primitive data type in Java?', 'int', 'float', 'String', 'boolean', 'C'),
(5, 'What is the default value of a boolean variable in Java?', 'true', 'false', '0', 'null', 'B'),
(5, 'Which keyword is used to define a class in Java?', 'class', 'struct', 'interface', 'object', 'A'),

-- Câu hỏi cho exam_id = 6 (Python Quiz)
(6, 'Which of the following is used to define a block of code in Python?', 'Indentation', 'Braces {}', 'Brackets []', 'Parentheses ()', 'A'),
(6, 'What is the output of print(type(3.14)) in Python?', 'int', 'float', 'double', 'number', 'B'),
(6, 'Which keyword is used to define a function in Python?', 'def', 'function', 'define', 'func', 'A'),

-- Câu hỏi cho exam_id = 7 (Data Structures Midterm)
(7, 'Which data structure uses FIFO principle?', 'Stack', 'Queue', 'Array', 'Linked List', 'B'),
(7, 'What is the time complexity of accessing an element in an array?', 'O(1)', 'O(n)', 'O(log n)', 'O(n^2)', 'A'),
(7, 'Which of the following is a non-linear data structure?', 'Array', 'Linked List', 'Tree', 'Stack', 'C'),

-- Câu hỏi cho exam_id = 8 (Algorithms Final)
(8, 'What is the time complexity of binary search?', 'O(1)', 'O(n)', 'O(log n)', 'O(n^2)', 'C'),
(8, 'Which sorting algorithm has the worst time complexity?', 'Merge Sort', 'Quick Sort', 'Bubble Sort', 'Heap Sort', 'C'),
(8, 'What is the main advantage of using a hash table?', 'Fast lookups', 'Easy to implement', 'Low memory usage', 'Supports sorting', 'A');