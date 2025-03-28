package dao;

import dto.CartDTO;
import dto.ProductDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartDAO {

    public boolean create(CartDTO entity) {
        String sql = "INSERT INTO [dbo].[Cart] (user_id, product_id, quantity, added_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entity.getUser_id());
            ps.setInt(2, entity.getProduct_id());
            ps.setInt(3, entity.getQuantity());
            ps.setDate(4, entity.getAddedAt());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<CartDTO> readAll() {
        String sql = "SELECT c.cart_id, c.user_id, c.product_id, c.quantity, c.added_at, "
                + "p.title, p.author, p.price, p.stock, p.image, p.category_id, p.description "
                + "FROM Cart c JOIN Product p ON c.product_id = p.product_id";

        List<CartDTO> list = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

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

                list.add(new CartDTO(
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDate("added_at"),
                        product));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete(int user_id, int product_id) {
        String sql = "DELETE FROM Cart WHERE user_id = ? and product_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user_id);
            ps.setInt(2, product_id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(CartDTO entity) {
        String sql = "UPDATE Cart SET user_id=?, product_id=?, quantity=?, added_at=? WHERE cart_id=?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entity.getUser_id());
            ps.setInt(2, entity.getProduct_id());
            ps.setInt(3, entity.getQuantity());
            ps.setDate(4, entity.getAddedAt());
            ps.setInt(5, entity.getCart_id());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public CartDTO readByID(Integer id) {
        String sql = "SELECT * FROM Cart WHERE cart_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CartDTO(
                            rs.getInt("cart_id"),
                            rs.getInt("user_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getDate("added_at"),
                            null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private boolean isUserExists(int user_id) {
        String sql = "SELECT COUNT(*) FROM Users WHERE user_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addToCart(int user_id, int product_id, int quantity) {
        if (!isUserExists(user_id)) {
            System.out.println("Error: User ID không tồn tại!");
            return false;
        }
        String sql = "MERGE INTO Cart AS target "
                + "USING (SELECT ? AS user_id, ? AS product_id) AS source "
                + "ON target.user_id = source.user_id AND target.product_id = source.product_id "
                + "WHEN MATCHED THEN "
                + "UPDATE SET quantity = target.quantity + ? "
                + "WHEN NOT MATCHED THEN "
                + "INSERT (user_id, product_id, quantity, added_at) VALUES (?, ?, ?, GETDATE());";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user_id);
            ps.setInt(2, product_id);
            ps.setInt(3, quantity);
            ps.setInt(4, user_id);
            ps.setInt(5, product_id);
            ps.setInt(6, quantity);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<CartDTO> getCartByUserID(int user_id) {
        String sql = "SELECT c.cart_id, c.user_id, c.product_id, c.quantity, c.added_at, "
                + "p.title, p.author, p.price, p.stock, p.image, p.category_id, p.description "
                + "FROM Cart c "
                + "JOIN Product p ON c.product_id = p.product_id "
                + "WHERE c.user_id = ?";
        List<CartDTO> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user_id);
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

                CartDTO cart = new CartDTO(
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDate("added_at"),
                        product);
                list.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean removeFromCart(int user_id, int product_id) {
        String sql = "DELETE FROM Cart WHERE user_id = ? AND  product_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user_id);
            ps.setInt(2, product_id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateCart(int user_id, int product_id, int quantity) throws ClassNotFoundException {
        String sql = "UPDATE Cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, user_id);
            ps.setInt(3, product_id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CartDTO getCart(int userId, int productId) {
        CartDTO cart = null;
        String sql = "SELECT c.cart_id, c.user_id, c.product_id, c.quantity, c.added_at, "
                + "p.title, p.price, p.stock, p.image, p.category_id, p.description "
                + "FROM Cart c JOIN Product p ON c.product_id = p.product_id "
                + "WHERE c.user_id = ? AND c.product_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setProduct_id(productId);
                product.setTitle(rs.getString("title"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setImage(rs.getString("image"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setDescription(rs.getString("description"));

                cart = new CartDTO();
                cart.setCart_id(rs.getInt("cart_id"));
                cart.setUser_id(rs.getInt("user_id"));
                cart.setProduct_id(productId);
                cart.setQuantity(rs.getInt("quantity"));
                cart.setAddedAt(rs.getDate("added_at"));
                cart.setProduct(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    public boolean updateCartQuantity(int user_id, int product_id, int quantity) {
        String sql = "UPDATE Cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, user_id);
            ps.setInt(3, product_id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
