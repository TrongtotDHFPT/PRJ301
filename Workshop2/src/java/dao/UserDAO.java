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
import utils.DBUtils;

/**
 *
 * @author trong
 */
public class UserDAO implements IDAO<UserDTO, String> {

    @Override
    public List<UserDTO> readAll() {
        String sql = "SELECT * FROM tblUsers";
        List<UserDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role"));
                list.add(user);
            }

        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public UserDTO readByID(String id) {
        String sql = "SELECT * FROM tblUsers WHERE [username] = ? ";

        try {
            Connection con;
            con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role"));
                return user;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean create(UserDTO entity) {
        String sql = "INSERT INTO tblUsers"
                + "([username],[name], [password],[role]) "
                + "VALUES (?,?,?,?)";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getRole());

            int ketQua = ps.executeUpdate();
            return ketQua > 0;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM tblUsers WHERE [username] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            int ketQua = ps.executeUpdate();
            return ketQua > 0;

        } catch (Exception e) {
        }
        return false;
    }

    public boolean update(UserDTO entity) {
        String sql = "UPDATE tblUsers SET"
                + "[name] = ?,"
                + "[password] = ?,"
                + "[role] = ?"
                + "WHERE [username] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(4, entity.getUsername());
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getRole());
            int ketQua = ps.executeUpdate();
            return ketQua > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<UserDTO> search(String searchTerm) {
        String sql = "SELECT * FROM tblUsers WHERE"
                + " [username] like ?"
                + " OR [name] like "
                + " OR [role] like ?";
        List<UserDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            ps.setString(3, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("role"));
                list.add(user);
            }
        } catch (Exception e) {
        }
        return list;
    }

}
