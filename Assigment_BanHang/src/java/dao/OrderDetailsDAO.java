package dao;

import dto.OrderDetailsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class OrderDetailsDAO implements IDAO<OrderDetailsDTO, Integer> {

    @Override
    public boolean create(OrderDetailsDTO entity) {
        String sql = "INSERT INTO [dbo].[OrderDetails] (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entity.getOrder_id());
            ps.setInt(2, entity.getProduct_id());
            ps.setInt(3, entity.getQuantity());
            ps.setInt(4, entity.getUnit_price());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OrderDetailsDTO> readAll() {
        String sql = "SELECT * FROM [dbo].[OrderDetails]";
        List<OrderDetailsDTO> list = new ArrayList<>();
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OrderDetailsDTO detail = new OrderDetailsDTO(
                        rs.getInt("order_detail_id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("unit_price")
                );
                list.add(detail);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM [dbo].[OrderDetails] WHERE [order_detail_id] = ?";
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
    public boolean update(OrderDetailsDTO entity) {
        String sql = "UPDATE [dbo].[OrderDetails] SET order_id = ?, product_id = ?, quantity = ?, unit_price = ? WHERE order_detail_id = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entity.getOrder_id());
            ps.setInt(2, entity.getProduct_id());
            ps.setInt(3, entity.getQuantity());
            ps.setInt(4, entity.getUnit_price());
            ps.setInt(5, entity.getOrder_detail_id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OrderDetailsDTO readByID(Integer id) {
        String sql = "SELECT * FROM [dbo].[OrderDetails] WHERE [order_detail_id] = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new OrderDetailsDTO(
                            rs.getInt("order_detail_id"),
                            rs.getInt("order_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getInt("unit_price")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetailsDTO> search(String searchTerm) {
        String sql = "SELECT * FROM [dbo].[OrderDetails] WHERE CAST(order_id AS VARCHAR) LIKE ? OR CAST(product_id AS VARCHAR) LIKE ?";
        List<OrderDetailsDTO> list = new ArrayList<>();
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetailsDTO detail = new OrderDetailsDTO(
                            rs.getInt("order_detail_id"),
                            rs.getInt("order_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getInt("unit_price")
                    );
                    list.add(detail);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
