/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookDTO;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.BDUtils;

/**
 *
 * @author trong
 */
public class BookDAO implements IDAO<BookDTO , String> {

    @Override
    public BookDTO readByID(String id) {
        String sql = "SELECT * FROM tblBooks WHERE BookID = ?";
        try {
            Connection con = BDUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
             BookDTO book = new BookDTO(
                     rs.getString("BookID"),
                     rs.getString("Title"),
                     rs.getString("Author"),
                     rs.getInt("PublishYear"),
                     rs.getDouble("Price"),
                     rs.getInt("Quantity"));
             return book;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      return null;
    }
    public List<BookDTO> searchByTitle(String searchTerm) throws ClassNotFoundException, SQLException{
        List<BookDTO> list = new ArrayList<>();
        String sql = "SELECT *  FROM tblBooks WHERE Title LIKE ? AND Quantity >0";
        Connection con = BDUtils.getConnection();
        PreparedStatement ps = con.prepareCall(sql);
        ps.setString(1,"%"+searchTerm+"%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            BookDTO book = new BookDTO(
                     rs.getString("BookID"),
                     rs.getString("Title"),
                     rs.getString("Author"),
                     rs.getInt("PublishYear"),
                     rs.getDouble("Price"),
                     rs.getInt("Quantity"));
            list.add(book);
        }
        return list;
    }
    
    public boolean setQuantityToZero(String id) throws SQLException{
        String sql = "UPDATE tblBooks SET Quantity =0 WHERE BookID =? ";
        try {
            Connection c = BDUtils.getConnection();
            PreparedStatement ps = c.prepareCall(sql);
            ps.setString(1,id);
            int i =ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
    
}
