package dao;

import dto.OrderDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class OrderDAO implements IDAO<OrderDTO, Integer> {

    @Override
    public boolean create(OrderDTO entity) {
        String sql = "INSERT INTO [dbo].[Orders] (user_id, total_amount, status, created_at) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entity.getUser_id());
            ps.setInt(2, entity.getTotal_amount());
            ps.setString(3, entity.getStatus());
            ps.setDate(4, entity.getCreated_at());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OrderDTO> readAll() {
        String sql = "SELECT * FROM [dbo].[Orders]";
        List<OrderDTO> list = new ArrayList<>();
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OrderDTO order = new OrderDTO(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getInt("total_amount"),
                        rs.getString("status"),
                        rs.getDate("created_at")
                );
                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM [dbo].[Orders] WHERE [order_id] = ?";
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
    public boolean update(OrderDTO entity) {
        String sql = "UPDATE [dbo].[Orders] SET user_id = ?, total_amount = ?, status = ?, created_at = ?"
                + " WHERE order_id = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entity.getUser_id());
            ps.setInt(2, entity.getTotal_amount());
            ps.setString(3, entity.getStatus());
            ps.setDate(4, entity.getCreated_at());
            ps.setInt(5, entity.getOrder_id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OrderDTO readByID(Integer id) {
        String sql = "SELECT * FROM [dbo].[Orders]"
                + " WHERE order_id = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new OrderDTO(
                            rs.getInt("order_id"),
                            rs.getInt("user_id"),
                            rs.getInt("total_amount"),
                            rs.getString("status"),
                            rs.getDate("created_at")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDTO> search(String searchTerm) {
        String sql = "SELECT * FROM [dbo].[Orders]"
                + " WHERE status LIKE ?";
        List<OrderDTO> list = new ArrayList<>();
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + searchTerm + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDTO order = new OrderDTO(
                            rs.getInt("order_id"),
                            rs.getInt("user_id"),
                            rs.getInt("total_amount"),
                            rs.getString("status"),
                            rs.getDate("created_at")
                    );
                    list.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
