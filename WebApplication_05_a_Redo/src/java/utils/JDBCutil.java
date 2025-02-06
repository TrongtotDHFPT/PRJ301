/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author trong
 */
public class JDBCutil {
    private static String DB_Name ="Wed_05_a";
    private static String DB_User_Name ="sa";
    private static String DB_Password ="12345";
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection c = null;
        
       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       String url = "jdbc:sqlserver://TT:1433;databaseName=Web_05_a";
       c = DriverManager.getConnection(url,DB_User_Name , DB_Password);
        return c;
    }
    
    public static void closeConnection(Connection c) throws ClassNotFoundException,SQLException{
        try {
            if(c!=null){
                System.out.println("Close Connection!");
            c.close();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
