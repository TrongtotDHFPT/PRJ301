/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.BDUtils;

/**
 *
 * @author trong
 */
public class UserDAO implements IDAO<UserDTO , String>{

    

    @Override
    public UserDTO readByID(String id) {
        String sql = "SELECT * FROM tblUsers WHERE userID =?";
        try {
            Connection con = BDUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
             UserDTO user = new UserDTO(
                     rs.getString("userID"),
                     rs.getString("fullName"),
                     rs.getString("roleID"),
                     rs.getString("password") );
             return user;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      return null;
    }
}
