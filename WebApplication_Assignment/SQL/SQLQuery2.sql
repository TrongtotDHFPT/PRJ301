SELECT TOP (1000) [ID]
      ,[Username]
      ,[Name]
      ,[Password]
      ,[Role]
  FROM [XShopSneaker_Database].[dbo].[tblAccount]

  SELECT * FROM tblAccount
  select * from tblAccount where Username ='alex_jordan'
  SELECT Name FROM tblAccount WHERE ID > 0
  INSERT INTO tblAccount (Username, Name, Password, Role) VALUES('a','a','a','USER')
