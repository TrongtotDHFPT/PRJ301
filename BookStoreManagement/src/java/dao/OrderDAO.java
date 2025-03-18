/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.OrderDTO;
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
public class OrderDAO {
    private Connection conn;

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    public List<OrderDTO> readAll() throws SQLException {
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                orders.add(new OrderDTO(
                    rs.getInt("order_id"),
                    rs.getInt("user_id"),
                    rs.getDouble("total_price"),
                    rs.getString("status")
                ));
            }
        }
        return orders;
    }

    public boolean create(OrderDTO order) throws SQLException {
        String sql = "INSERT INTO Orders (user_id, total_price, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, order.getUser_id());
            stmt.setDouble(2, order.getTotal_price());
            stmt.setString(3, order.getStatus());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(OrderDTO order) throws SQLException {
        String sql = "UPDATE Orders SET user_id = ?, total_price = ?, status = ? WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, order.getUser_id());
            stmt.setDouble(2, order.getTotal_price());
            stmt.setString(3, order.getStatus());
            stmt.setInt(4, order.getOrder_id());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int order_id) throws SQLException {
        String sql = "DELETE FROM Orders WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, order_id);
            return stmt.executeUpdate() > 0;
        }
    }
}
