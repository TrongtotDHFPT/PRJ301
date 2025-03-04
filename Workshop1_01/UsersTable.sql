/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  trong
 * Created: Feb 27, 2025
 */
CREATE TABLE tblUsers (
    Username VARCHAR(50) PRIMARY KEY,
    [Name] VARCHAR(100) NOT NULL,
    [Password] VARCHAR(255) NOT NULL,
    [Role] VARCHAR(20) NOT NULL CHECK (Role IN ('Founder', 'Team Member'))
)
INSERT INTO tblUsers (Username, Name, Password, Role) VALUES
-- Founders
('founder01', 'Elon Musk', 'hashed_password_1', 'Founder'),
('founder02', 'Jeff Bezos', 'hashed_password_2', 'Founder'),

-- Team Members
('team01', 'Alice Johnson', 'hashed_password_3', 'Team Member'),
('team02', 'Bob Williams', 'hashed_password_4', 'Team Member'),
('team03', 'Charlie Brown', 'hashed_password_5', 'Team Member'),
('team04', 'David Kim', 'hashed_password_6', 'Team Member'),
('team05', 'Emma Watson', 'hashed_password_7', 'Team Member');
