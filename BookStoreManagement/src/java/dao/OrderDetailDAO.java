package dao;

import dto.OrderDetailDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailDAO implements IDAO<OrderDetailDTO, Integer> {

    @Override
    public boolean create(OrderDetailDTO entity) {
        String sql = "INSERT INTO OrderDetails (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entity.getOrder_id());
            ps.setInt(2, entity.getProduct_id());
            ps.setInt(3, entity.getQuantity());
            ps.setDouble(4, entity.getPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return false;
    }

    @Override
    public List<OrderDetailDTO> readAll() {
        String sql = "SELECT * FROM OrderDetails";
        List<OrderDetailDTO> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new OrderDetailDTO(
                    rs.getInt("order_detail_id"),
                    rs.getInt("order_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return list;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM OrderDetails WHERE order_detail_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return false;
    }

    @Override
    public boolean update(OrderDetailDTO entity) {
        String sql = "UPDATE OrderDetails SET order_id=?, product_id=?, quantity=?, price=? WHERE order_detail_id=?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entity.getOrder_id());
            ps.setInt(2, entity.getProduct_id());
            ps.setInt(3, entity.getQuantity());
            ps.setDouble(4, entity.getPrice());
            ps.setInt(5, entity.getOrder_detail_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return false;
    }

    @Override
    public OrderDetailDTO readByID(Integer id) {
        String sql = "SELECT * FROM OrderDetails WHERE order_detail_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new OrderDetailDTO(
                        rs.getInt("order_detail_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return null;
    }

    @Override
    public List<OrderDetailDTO> search(String searchTerm) {
        return new ArrayList<>(); // Không có trường nào phù hợp để tìm kiếm theo chuỗi
    }
}
