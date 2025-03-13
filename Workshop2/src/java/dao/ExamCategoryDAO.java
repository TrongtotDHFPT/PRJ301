/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ExamCategoryDTO;
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
public class ExamCategoryDAO implements IDAO<ExamCategoryDTO , Integer>{

    @Override
    public List<ExamCategoryDTO> readAll() {
        String sql = "SELECT *  FROM [tblExamCategories]";
        List<ExamCategoryDTO> list = new  ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ExamCategoryDTO exCateDTO = new ExamCategoryDTO(
                        rs.getInt("category_id"),
                        rs.getString("category_name"), 
                        rs.getString("description"));
                list.add(exCateDTO);
            }
            
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public ExamCategoryDTO readByID(Integer id) {
        String sql = "SELECT * FROM [tblExamCategories] WHERE [category_id] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement  ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();;
            while(rs.next()){
                ExamCategoryDTO exCateDTO = new ExamCategoryDTO(
                        rs.getInt("category_id"),
                        rs.getString("category_name"), 
                        rs.getString("description"));
                return exCateDTO;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean create(ExamCategoryDTO entity) {
        String sql = "INSERT INTO [tblExamCategories]"
                + "([category_id], [category_name],[description]) "
                + "VALUES (?,?,?)";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getCategory_id());
            ps.setString(2, entity.getCategory_name());
            ps.setString(3, entity.getDescription());
            int ketQua = ps.executeUpdate();
            return ketQua > 0;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM tblExamCategories WHERE [category_id] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int ketQua = ps.executeUpdate();
            return ketQua > 0;

        } catch (Exception e) {
        }
        return false;
    }


    @Override
    public List<ExamCategoryDTO> search(String searchTerm) {
        String sql = "SELECT * FROM ExamCategoryDTO WHERE"
                + " [category_name] like ?"
                + " OR [description] like ? ";
        List<ExamCategoryDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamCategoryDTO exCateDTO = new ExamCategoryDTO(
                        rs.getInt("category_id"),
                        rs.getString("category_name"), 
                        rs.getString("description"));
                list.add(exCateDTO);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean update(ExamCategoryDTO entity) {
        String sql = "UPDATE [tblExamCategories] SET"
                + "[category_name] = ?,"
                + "[description] = ?,"
                + "WHERE [category_id] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getCategory_name());
            ps.setString(2, entity.getDescription());
            ps.setInt(3, entity.getCategory_id());
            int ketQua = ps.executeUpdate();
            return ketQua > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExamCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExamCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
