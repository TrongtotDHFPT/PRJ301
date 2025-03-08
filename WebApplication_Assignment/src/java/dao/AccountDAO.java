/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AccountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author trong
 */
public class AccountDAO implements IDAO<AccountDTO, Integer> {

    @Override
    public List<AccountDTO> readByAll() {
        List<AccountDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblAccount";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet  rs = ps.executeQuery();
            while(rs.next()){
               AccountDTO account = new AccountDTO(
                        rs.getInt("ID"),
                        rs.getString("Username"),
                        rs.getString("Name"),
                        rs.getString("Password"),
                        rs.getString("Role"));
               list.add(account);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean create(AccountDTO entity) {
        String sql = "INSERT INTO tblAccount (Username, Name, Password, Role) VALUES(?,?,?,?) ";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,entity.getUserName());
            ps.setString(2,entity.getName());
            ps.setString(3,entity.getPassWord());
            ps.setString(4,entity.getRole());
            int result  = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AccountDTO readByUsername(String strUserName) {
        String sql = "select * from tblAccount where Username =?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, strUserName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountDTO account = new AccountDTO(
                        rs.getInt("ID"),
                        rs.getString("Username"),
                        rs.getString("Name"),
                        rs.getString("Password"),
                        rs.getString("Role"));
                return account;

            }
        } catch (Exception e) {
        }
        return null;
    }

}
