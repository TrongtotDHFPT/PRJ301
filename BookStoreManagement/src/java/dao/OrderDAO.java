package dao;

import dto.OrderDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO implements IDAO<OrderDTO, Integer> {

    @Override
    public boolean create(OrderDTO entity) {
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

    @Override
    public List<OrderDTO> readAll() {
        String sql = "SELECT * FROM Orders";
        List<OrderDTO> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new OrderDTO(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("total_price"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM Orders WHERE order_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return false;
    }

    @Override
    public boolean update(OrderDTO entity) {
        String sql = "UPDATE Orders SET user_id = ?, total_price = ? WHERE order_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entity.getUser_id());
            ps.setDouble(2, entity.getTotal_price());
            ps.setInt(3, entity.getOrder_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public OrderDTO readByID(Integer id) {
        String sql = "SELECT * FROM Orders WHERE order_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new OrderDTO(
                            rs.getInt("order_id"),
                            rs.getInt("user_id"),
                            rs.getDouble("total_price"),
                            rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return null;
    }

    @Override
    public List<OrderDTO> search(String searchTerm) {
        return new ArrayList<>(); // Không có trường nào để search theo chuỗi
    }
}
