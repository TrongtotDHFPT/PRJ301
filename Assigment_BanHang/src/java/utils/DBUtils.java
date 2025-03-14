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
public class DBUtils {

    public static final String DB_NAME = "";
    public static final String DB_USERNAME = "";
    public static final String DB_PASSWORD = "";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection con ;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://;databaseName="+DB_NAME;
        con = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
        return con;
    }
}
