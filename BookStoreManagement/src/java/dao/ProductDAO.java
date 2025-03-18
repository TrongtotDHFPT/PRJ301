/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ProductDTO;
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
public class ProductDAO {
    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public List<ProductDTO> readAll() throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(new ProductDTO(
                    rs.getInt("product_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("image"),
                    rs.getInt("category_id"),
                    rs.getString("description")
                ));
            }
        }
        return products;
    }

    public boolean create(ProductDTO product) throws SQLException {
        String sql = "INSERT INTO Product (title, author, price, stock, image, category_id, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getTitle());
            stmt.setString(2, product.getAuthor());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setString(5, product.getImage());
            stmt.setInt(6, product.getCategory_id());
            stmt.setString(7, product.getDescription());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(ProductDTO product) throws SQLException {
        String sql = "UPDATE Product SET title = ?, author = ?, price = ?, stock = ?, image = ?, category_id = ?, description = ? WHERE product_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getTitle());
            stmt.setString(2, product.getAuthor());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setString(5, product.getImage());
            stmt.setInt(6, product.getCategory_id());
            stmt.setString(7, product.getDescription());
            stmt.setInt(8, product.getProduct_id());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(int product_id) throws SQLException {
        String sql = "DELETE FROM Product WHERE product_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product_id);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<ProductDTO> searchByTerm(String searchTerm) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE title LIKE ? OR author LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("image"),
                        rs.getInt("category_id"),
                        rs.getString("description")
                    ));
                }
            }
        }
        return products;
    }
}
