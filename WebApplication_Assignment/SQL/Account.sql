/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  trong
 * Created: Mar 8, 2025
 */

CREATE TABLE tblAccount (
    ID INT IDENTITY(1,1) PRIMARY KEY,  -- Cột ID là khóa chính, tự động tăng
    Username VARCHAR(50) NOT NULL UNIQUE,  -- Tên đăng nhập, không được trùng
    Name VARCHAR(100) NOT NULL,  -- Họ và tên người dùng
    Password VARCHAR(255) NOT NULL,  -- Mật khẩu
    Role VARCHAR(20) NOT NULL CHECK (Role IN ('USER', 'ADMIN'))  -- Chỉ nhận giá trị USER hoặc ADMIN
);
INSERT INTO tblAccount (Username, Name, Password, Role) VALUES
('alex_jordan', 'Alex Jordan', 'alexjordan123', 'USER'),
('bella_sanders', 'Bella Sanders', 'bellasanders123', 'ADMIN'),
('charlie_morgan', 'Charlie Morgan', 'charliemorgan123', 'USER'),
('diana_roberts', 'Diana Roberts', 'dianaroberts123', 'ADMIN'),
('evan_hughes', 'Evan Hughes', 'evanhughes123', 'USER'),
('fiona_bennett', 'Fiona Bennett', 'fionabennett123', 'ADMIN'),
('george_patterson', 'George Patterson', 'georgepatterson123', 'USER'),
('hannah_cooper', 'Hannah Cooper', 'hannahcooper123', 'ADMIN'),
('isaac_watson', 'Isaac Watson', 'isaacwatson123', 'USER'),
('julia_price', 'Julia Price', 'juliaprice123', 'ADMIN'),
('kevin_foster', 'Kevin Foster', 'kevinfoster123', 'USER'),
('lisa_murray', 'Lisa Murray', 'lisamurray123', 'ADMIN'),
('michael_dunn', 'Michael Dunn', 'michaeldunn123', 'USER'),
('natalie_brooks', 'Natalie Brooks', 'nataliebrooks123', 'ADMIN'),
('oliver_reed', 'Oliver Reed', 'oliverreed123', 'USER'),
('paige_harper', 'Paige Harper', 'paigeharper123', 'ADMIN'),
('quentin_haynes', 'Quentin Haynes', 'quentinhaynes123', 'USER'),
('rachel_owens', 'Rachel Owens', 'rachelowens123', 'ADMIN'),
('samuel_pierce', 'Samuel Pierce', 'samuelpierce123', 'USER'),
('tina_fleming', 'Tina Fleming', 'tinafleming123', 'ADMIN');
