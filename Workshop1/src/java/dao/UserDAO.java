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
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author trong
 */
public class UserDAO implements IDAO<UserDTO, String> {

    @Override
    public boolean create(UserDTO entity) {
        return true;
    }

    @Override
    public List<UserDTO> readAll() {
        return null;
    }

    @Override
    public UserDTO readByID(String id) {
        return null;
    }

    @Override
    public boolean update(UserDTO entity) {
        return true;
    }

    @Override
    public boolean delete(String id) {
        return true;
    }

    @Override
    public List<UserDTO> search(String searchTerm) {
        return null;
    }

    public UserDTO readByUsername(String strUsername) {
        String sql = "SELECT * FROM tblUsers WHERE Username=?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, strUsername);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("Username"),
                        rs.getString("Name"),
                        rs.getString("Password"),
                        rs.getString("Role"));
                return user;
            }
        } catch (Exception e) {
            
        }
        return null;
    }

}
