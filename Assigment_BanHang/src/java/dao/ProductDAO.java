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
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author trong
 */
public class ProductDAO implements IDAO<ProductDTO, Integer> {

    @Override
    public boolean create(ProductDTO entity) {
        String sql = "INSERT INTO [dbo].[Products] (product_name, description, price, stock_quantity, image, category_id)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getProduct_name());
            ps.setString(2, entity.getDescription());
            ps.setInt(3, entity.getPrice());
            ps.setInt(4, entity.getStock_quantity());
            ps.setString(5, entity.getImage());
            ps.setInt(6, entity.getCategory_id());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List<ProductDTO> readAll() {
        String sql = "SELECT *FROM [dbo].[Products]";
        List<ProductDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("image"),
                        rs.getInt("category_id"));
                list.add(product);
            }

        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE  FROM [dbo].[Products] WHERE [product_id] = ?  ";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean update(ProductDTO entity) {
        String sql = "UPDATE [dbo].[Products] SET "
                + "[product_name] = ?,"
                + "[description] = ?,"
                + "[price] = ?,"
                + "[stock_quantity] = ?,"
                + "[image] = ?,"
                + "[category_id] = ?"
                + "WHERE [product_id] = ? ";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getProduct_name());
            ps.setString(2, entity.getDescription());
            ps.setInt(3, entity.getPrice());
            ps.setInt(4, entity.getStock_quantity());
            ps.setString(5, entity.getImage());
            ps.setInt(6, entity.getCategory_id());
            ps.setInt(7, entity.getProduct_id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public ProductDTO readByID(Integer id) {
        String sql = "SELECT *FROM [dbo].[Products] "
                + "WHERE  [product_id] = ? ";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("image"),
                        rs.getInt("category_id"));
                return product;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<ProductDTO> search(String searchTerm) {
        String sql = "SELECT [product_id], [product_name],[description],[price],[stock_quantity],[image],[category_id]"
                + "FROM [dbo].[Products]"
                + "WHERE [product_name] LIKE ? "
                + "OR [description] LIKE ? ";
        List<ProductDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("image"),
                        rs.getInt("category_id"));
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
