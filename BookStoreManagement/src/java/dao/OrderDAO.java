package dao;

import dto.OrderDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {

//    @Override
    public boolean createOrder(OrderDTO entity) {
        String sql = "INSERT INTO Orders (user_id, total_price) VALUES (?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entity.getUser_id());
            ps.setDouble(2, entity.getTotal_price());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<OrderDTO> getOrdersByUser(int userId) throws ClassNotFoundException {
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT order_id, user_id, total_price, status FROM Orders WHERE user_id = ? ORDER BY order_id DESC";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                double totalPrice = rs.getDouble("total_price");
                String status = rs.getString("status");

                orders.add(new OrderDTO(orderId, userId, totalPrice, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
