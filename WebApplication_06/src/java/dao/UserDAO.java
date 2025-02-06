/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author trong
 */
public class UserDAO implements IDAO<UserDTO ,String> {

    @Override
    public boolean create(UserDTO entity) {
        String sql = "INSERT [dbo].[tblUsers] ([userID], [fullName], [roleID], [password]) "
                + "VALUES (N'"+entity.getUserID()
                +"', N'"+entity.getFullName()
                +"', N'"+entity.getRoleID()
                +"', N'"+entity.getPassword()+"')";
        try {
            Connection con  = DBUtils.getConnection();
            Statement st = con.createStatement();  
            int soDongThayDoi = st.executeUpdate(sql);
            return soDongThayDoi > 0;
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<UserDTO> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO readById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(UserDTO entity) {
        String sql = "update [dbo].[tblUsers] set "
                + "[fullName] = N'"+entity.getFullName()+"',"
                + "[password] = N'"+entity.getPassword()+"',"
                + "[roleID] = N'"+entity.getRoleID()+"'"
                + "Where [userID] = N'" +entity.getUserID()+"'";
        try {
            Connection con  = DBUtils.getConnection();
            Statement st = con.createStatement();  
            
            int  rs = st.executeUpdate(sql);
            return rs > 0;
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "delete from [dbo].[tblUsers] "
                + "where [userID] =N'" +id+"'" ;
        Connection con;
        try {
            con = DBUtils.getConnection();
            Statement st = con.createStatement();
            
            int rs =  st.executeUpdate(sql);
            return rs > 0;
            
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<UserDTO> search(String seachTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
