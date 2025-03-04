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


CREATE TABLE tblStarupProjects (
    Project_id INT PRIMARY KEY,
    Project_name VARCHAR(100) NOT NULL,
    [Description] TEXT ,
    [Status] VARCHAR(20) NOT NULL CHECK ([Status] IN ('Ideation','Development','Launch','Scaling')),
	Estimated_launch DATE NOT  NULL
)

