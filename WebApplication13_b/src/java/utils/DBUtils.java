/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author trong
 */
public class DBUtils {

    public static final String DB_NAME = "prj301_1820_slot8";
    public static final String DB_USERNAME = "sa";
    public static final String DB_PASSWORD = "";
    public Connection getConnection(){
        Connection con = null ;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc..SQLServerDriver");
            String url = "jdbc:sqlserver://;databaseName="+DB_NAME;
            con = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
        }
        return con;
    }

}
