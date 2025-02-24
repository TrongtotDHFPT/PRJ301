/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author trong
 */
public class BookDAO implements IDAO<BookDTO , String> {

    @Override
    public List<BookDTO> readByTitle(String searchTerm) {
        List<BookDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblBooks WHERE [Title] LIKE ? AND Quantity > 0";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    
    }   

    @Override
    public BookDTO readByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean updateQuantityToZero(String id) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE tblBooks SET Quantity = 0 Where BookID = ?";
        Connection con = DBUtils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        int i = ps.executeUpdate();
        return i > 0;
    }
    
    
}
