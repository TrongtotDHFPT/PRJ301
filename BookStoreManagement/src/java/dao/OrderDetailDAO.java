/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.OrderDetailDTO;
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
public class OrderDetailDAO {

    private Connection conn;

    public OrderDetailDAO(Connection conn) {
        this.conn = conn;
    }

    public List<OrderDetailDTO> readAll() throws SQLException {
        List<OrderDetailDTO> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                orderDetails.add(new OrderDetailDTO(
                        rs.getInt("order_detail_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                ));
            }
        }
        return orderDetails;
    }

    public boolean create(OrderDetailDTO orderDetail) throws SQLException {
        String sql = "INSERT INTO OrderDetails (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDetail.getOrder_id());
            stmt.setInt(2, orderDetail.getProduct_id());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setDouble(4, orderDetail.getPrice());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(OrderDetailDTO orderDetail) throws SQLException {
        String sql = "UPDATE OrderDetails SET order_id = ?, product_id = ?, quantity = ?, price = ? WHERE order_detail_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDetail.getOrder_id());
            stmt.setInt(2, orderDetail.getProduct_id());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setDouble(4, orderDetail.getPrice());
            stmt.setInt(5, orderDetail.getOrder_detail_id());
            return stmt.executeUpdate() > 0;
        }   
    }

    public boolean delete(int order_detail_id) throws SQLException {
        String sql = "DELETE FROM OrderDetails WHERE order_detail_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, order_detail_id);
            return stmt.executeUpdate() > 0;
        }
    }
}
