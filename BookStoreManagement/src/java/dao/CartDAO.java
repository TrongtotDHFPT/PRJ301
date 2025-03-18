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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trong
 */
public class CartDAO {

    private Connection conn;

    public CartDAO(Connection conn) {
        this.conn = conn;
    }

    public List<CartDTO> readAll() throws SQLException {
        List<CartDTO> carts = new ArrayList<>();
        String sql = "SELECT * FROM Cart";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                carts.add(new CartDTO(
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDate("added_at")
                ));
            }
        }
        return carts;
    }

    public boolean create(CartDTO cart) throws SQLException {
        String sql = "INSERT INTO Cart (user_id, product_id, quantity, added_at) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cart.getUser_id());
            stmt.setInt(2, cart.getProduct_id());
            stmt.setInt(3, cart.getQuantity());
            stmt.setDate(4, cart.getAdded_at());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(CartDTO cart) throws SQLException {
        String sql = "UPDATE Cart SET user_id = ?, product_id = ?, quantity = ?, added_at = ? WHERE cart_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cart.getUser_id());
            stmt.setInt(2, cart.getProduct_id());
            stmt.setInt(3, cart.getQuantity());
            stmt.setDate(4, cart.getAdded_at());
            stmt.setInt(5, cart.getCart_id());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int cart_id) throws SQLException {
        String sql = "DELETE FROM Cart WHERE cart_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cart_id);
            return stmt.executeUpdate() > 0;
        }
    }
}
