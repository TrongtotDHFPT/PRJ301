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
public class BDUtils {
    public static final String DB_NAME = "prj301_1820_slot8";
    public static final String DB_USERNAME = "sa";
    public static final String DB_PAPSWORD = "12345";
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection c = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String sql = "jdbc:sqlserver://;databaseName="+DB_NAME;
        c = DriverManager.getConnection(sql, DB_USERNAME, DB_PAPSWORD);
        return c;
    }
}
