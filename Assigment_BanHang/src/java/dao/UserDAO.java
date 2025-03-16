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
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author trong
 */
public class UserDAO implements IDAO<UserDTO, Integer> {

    @Override
    public boolean create(UserDTO entity) {
        String sql = "INSERT INTO [dbo].[Users]"
                + "(name, username, password, role, email, phone, address) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getUsername());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getRole());
            ps.setString(5, entity.getEmail());
            ps.setString(6, entity.getPhone());
            ps.setString(7, entity.getAddress());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List<UserDTO> readAll() {
        String sql = "SELECT *FROM [dbo].[Users] ";
        List<UserDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"));
                list.add(user);
            }

        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE  FROM [dbo].[Users] WHERE [user_id] = ?  ";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean update(UserDTO entity) {
        String sql = "UPDATE [dbo].[Users] SET "
                + "[username] = ?, "
                + "[name] = ?,"
                + "[password] = ?,"
                + "[role] = ?,"
                + "[email] = ?,"
                + "[phone] = ?,"
                + "[address] = ?"
                + "WHERE [user_id] = ? ";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getRole());
            ps.setString(5, entity.getEmail());
            ps.setString(6, entity.getPhone());
            ps.setString(7, entity.getAddress());
            ps.setInt(8, entity.getUser_id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public UserDTO readByID(Integer id) {
        String sql = "SELECT *FROM [dbo].[Users] WHERE  [user_id] = ? ";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"));
                return user;
            }

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<UserDTO> search(String searchTerm) {
        String sql = "SELECT  [role],[email],[phone],[address]"
                + "FROM [dbo].[Users] "
                + "WHERE [name] LIKE ? "
                + "OR [username] LIKE ? ";
        List<UserDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"));
                list.add(user);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public UserDTO getUserByUsername(String strUsername) {
        String sql = "SELECT *FROM [dbo].[Users] WHERE  [username] = ? ";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, strUsername);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"));
                return user;
            }

        } catch (Exception e) {
        }
        return null;
    }

    public boolean insertUser(UserDTO user) {
        String sql = "INSERT INTO [dbo].[Users] "
                + "(name, username, password, role, email, phone, address) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, "USER");
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getAddress());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isExist(String strUsername){
        String sql = "SELECT username FROM [dbo].[Users] WHERE username = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, strUsername);
            ResultSet rs = ps.executeQuery();
            return rs.next(); //khác null => usernmae đã tồn tại 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false ;
    }

    

}
