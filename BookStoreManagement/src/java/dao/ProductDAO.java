package dao;

import dto.ProductDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
//implements IDAO<ProductDTO, Integer>

    public boolean create(ProductDTO entity) {
        String sql = "INSERT INTO Product (title, author, price, stock, image, category_id, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getAuthor());
            ps.setDouble(3, entity.getPrice());
            ps.setInt(4, entity.getStock());
            ps.setString(5, entity.getImage());
            ps.setInt(6, entity.getCategory_id());
            ps.setString(7, entity.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<ProductDTO> readAll() {
        String sql = "SELECT * FROM Product";
        List<ProductDTO> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new ProductDTO(
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
            System.out.println("chắc là oke");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM Product WHERE product_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(ProductDTO entity) {
        String sql = "UPDATE Product SET title=?, author=?, price=?, stock=?, image=?, category_id=?, description=? WHERE product_id=?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getAuthor());
            ps.setDouble(3, entity.getPrice());
            ps.setInt(4, entity.getStock());
            ps.setString(5, entity.getImage());
            ps.setInt(6, entity.getCategory_id());
            ps.setString(7, entity.getDescription());
            ps.setInt(8, entity.getProduct_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static ProductDTO getProductById(int product_id) {
        String sql = "SELECT * FROM Product WHERE product_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, product_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ProductDTO(
                            rs.getInt("product_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getDouble("price"),
                            rs.getInt("stock"),
                            rs.getString("image"),
                            rs.getInt("category_id"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ProductDTO> search(String searchTerm) {
        String sql = "SELECT * FROM Product WHERE title LIKE ? OR author LIKE ?";
        List<ProductDTO> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ProductDTO(
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ProductDTO> getProductByCategoryID(int category_id) {
        String sql = " SELECT * FROM Product WHERE category_id = ?";
        List<ProductDTO> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ProductDTO> searchBySearchAndCategoryID(String searchTerm, int category_id) {
        String sql = " SELECT * FROM Product  "
                + "WHERE (title LIKE ? OR author LIKE ?)"
                + " AND category_id = ? ";
        List<ProductDTO> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            ps.setInt(3, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductDTO(
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ProductDTO> readAllOrder(String orderByX) {//mặc định là asc
        String sql = "SELECT * FROM Product ORDER BY  [price] " + orderByX;
        List<ProductDTO> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new ProductDTO(
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ProductDTO getLatestProduct(int category_id) throws SQLException, ClassNotFoundException {
        String sql = "";
        if (category_id != -1) {
            sql = "SELECT TOP 1 * FROM Product "
                    + "WHERE category_id = ?"
                    + " ORDER BY product_id DESC";
        } else {
            sql = "SELECT TOP 1 * FROM Product ORDER BY product_id DESC";
        }

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            if (category_id != -1) {
                ps.setInt(1, category_id);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ProductDTO product = new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("image"),
                        rs.getInt("category_id"),
                        rs.getString("description"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ProductDTO> getProductsAndOrderBy(String searchTerm, int category_id, String orderBy) {
        List<ProductDTO> listP = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE title LIKE ? ";

        if (category_id != -1) {
            sql += " AND category_id = ? ";
        }

        if (orderBy.equals("asc")) {
            sql += " ORDER BY price ASC ";
        } else if (orderBy.equals("desc")) {
            sql += " ORDER BY price DESC ";
        }

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + searchTerm + "%");

            if (category_id != -1) {
                ps.setInt(2, category_id);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO(
                        rs.getInt("product_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("image"),
                        rs.getInt("category_id"),
                        rs.getString("description"));
                listP.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listP;
    }

    public boolean updateStock(int productId, int quantityPurchased) {
        String sql = "UPDATE [dbo].[Product] SET stock = stock - ? WHERE product_id = ? AND stock >= ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantityPurchased);
            ps.setInt(2, productId);
            ps.setInt(3, quantityPurchased);  

            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
