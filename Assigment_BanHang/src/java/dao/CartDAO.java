/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CartDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class CartDAO implements IDAO<CartDTO, Integer> {

    @Override
    public boolean create(CartDTO entity) {
        String sql = "INSERT INTO [dbo].[Cart] (user_id, product_id, quantity, added_at) "
                   + "VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entity.getUser_id());
            ps.setInt(2, entity.getProduct_id());
            ps.setInt(3, entity.getQuantity());
            ps.setDate(4, entity.getAdded_at());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CartDTO> readAll() {
        String sql = "SELECT * FROM [dbo].[Cart]";
        List<CartDTO> list = new ArrayList<>();
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CartDTO cart = new CartDTO(
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDate("added_at")
                );
                list.add(cart);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM [dbo].[Cart] WHERE cart_id = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(CartDTO entity) {
        String sql = "UPDATE [dbo].[Cart] SET "
                   + "user_id = ?, "
                   + "product_id = ?, "
                   + "quantity = ?, "
                   + "added_at = ? "
                   + "WHERE cart_id = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entity.getUser_id());
            ps.setInt(2, entity.getProduct_id());
            ps.setInt(3, entity.getQuantity());
            ps.setDate(4, entity.getAdded_at());
            ps.setInt(5, entity.getCart_id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CartDTO readByID(Integer id) {
        String sql = "SELECT * FROM [dbo].[Cart] WHERE cart_id = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CartDTO(
                            rs.getInt("cart_id"),
                            rs.getInt("user_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getDate("added_at")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CartDTO> search(String searchTerm) {
        String sql = "SELECT * FROM [dbo].[Cart] "
                   + "WHERE CAST(user_id AS NVARCHAR) LIKE ? "
                   + "OR CAST(product_id AS NVARCHAR) LIKE ?";
        List<CartDTO> list = new ArrayList<>();
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartDTO cart = new CartDTO(
                            rs.getInt("cart_id"),
                            rs.getInt("user_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getDate("added_at")
                    );
                    list.add(cart);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

