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
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author trong
 */
public class BookDAO implements IDAO<BookDTO, String>{

    @Override
    public boolean create(BookDTO entity) {
        return true;
    }

    @Override
    public List<BookDTO> readAll() {
        return null;
    }

    @Override
    public BookDTO readById(String id) {
        return null;
    }

    @Override
    public boolean update(BookDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        return true;
    }

    @Override
    public List<BookDTO> search(String searchTerm) {
        return null;
    }
    
    public List<BookDTO> searchByTitle(String searchTerm) {
        if(searchTerm.equals("") || searchTerm == null){//phòng trường hợp để trống
            return null;
        }
        String sql = "SELECT  * FROM  tblBooks WHERE [Title] LIKE ?";
        List<BookDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
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
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return list;
    }
}
