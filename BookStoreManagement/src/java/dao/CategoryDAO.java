/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CategoryDTO;
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
public class CategoryDAO {

    private Connection conn;

    public CategoryDAO(Connection conn) {
        this.conn = conn;
    }

    public List<CategoryDTO> readAll() throws SQLException {
        List<CategoryDTO> categories = new ArrayList<>();
        String sql = "SELECT * FROM Category";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categories.add(new CategoryDTO(rs.getInt("category_id"), rs.getString("name")));
            }
        }
        return categories;
    }

    public boolean create(CategoryDTO category) throws SQLException {
        String sql = "INSERT INTO Category (name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(CategoryDTO category) throws SQLException {
        String sql = "UPDATE Category SET name = ? WHERE category_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getCategory_id());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int category_id) throws SQLException {
        String sql = "DELETE FROM Category WHERE category_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, category_id);
            return stmt.executeUpdate() > 0;
        }
    }
}
