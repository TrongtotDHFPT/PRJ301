package dao;

import dto.ProductCategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class ProductCategoryDAO implements IDAO<ProductCategoryDTO, Integer> {

    @Override
    public boolean create(ProductCategoryDTO entity) {
        String sql = "INSERT INTO [dbo].[ProductCategories] (category_name, description) VALUES (?, ?)";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entity.getCategory_name());
            ps.setString(2, entity.getDescription());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace(); // Xem lỗi nếu có
        }
        return false;
    }

    @Override
    public List<ProductCategoryDTO> readAll() {
        String sql = "SELECT * FROM [dbo].[ProductCategories]";
        List<ProductCategoryDTO> list = new ArrayList<>();
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductCategoryDTO category = new ProductCategoryDTO(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("description")
                );
                list.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM [dbo].[ProductCategories] WHERE [category_id] = ?";
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
    public boolean update(ProductCategoryDTO entity) {
        String sql = "UPDATE [dbo].[ProductCategories] SET category_name = ?, description = ? WHERE category_id = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entity.getCategory_name());
            ps.setString(2, entity.getDescription());
            ps.setInt(3, entity.getCategory_id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ProductCategoryDTO readByID(Integer id) {
        String sql = "SELECT * FROM [dbo].[ProductCategories] WHERE category_id = ?";
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ProductCategoryDTO(
                            rs.getInt("category_id"),
                            rs.getString("category_name"),
                            rs.getString("description")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductCategoryDTO> search(String searchTerm) {
        String sql = "SELECT * FROM [dbo].[ProductCategories] WHERE category_name LIKE ? OR description LIKE ?";
        List<ProductCategoryDTO> list = new ArrayList<>();
        try (Connection con = DBUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductCategoryDTO category = new ProductCategoryDTO(
                            rs.getInt("category_id"),
                            rs.getString("category_name"),
                            rs.getString("description")
                    );
                    list.add(category);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
