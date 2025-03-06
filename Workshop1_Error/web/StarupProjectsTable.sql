/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  trong
 * Created: Feb 27, 2025
 */
CREATE TABLE tblStarupProjects (
    Project_id INT PRIMARY KEY,
    Project_name VARCHAR(100) NOT NULL,
    [Description] TEXT ,
    [Status] VARCHAR(20) NOT NULL CHECK ([Status] IN ('Ideation','Development','Launch','Scaling')),
	Estimated_launch DATE NOT  NULL
)
INSERT INTO tblStarupProjects (Project_id, Project_name, [Description], [Status], Estimated_launch) VALUES
(1, 'Tesla AI', 'Developing autonomous driving technology.', 'Development', '2025-06-15'),
(2, 'Amazon Drone Delivery', 'Deploying drones for e-commerce deliveries.', 'Scaling', '2024-12-20'),
(3, 'SpaceX Mars Mission', 'Planning the first human colony on Mars.', 'Ideation', '2030-01-01'),
(4, 'OpenAI GPT-5', 'Advancing AI language models.', 'Development', '2025-03-10'),
(5, 'Blue Origin Space Hotel', 'Luxury space hotel for tourists.', 'Ideation', '2035-05-20');

