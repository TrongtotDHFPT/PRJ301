/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JDBCutil;

/**
 *
 * @author trong
 */
public class Test_JDBCutil {
    public static void main(String args[]){
        try {
            Connection con = JDBCutil.getConnection();
            System.out.println(con);
            JDBCutil.closeConnection(con);
            System.out.println(con);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Test_JDBCutil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Test_JDBCutil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
