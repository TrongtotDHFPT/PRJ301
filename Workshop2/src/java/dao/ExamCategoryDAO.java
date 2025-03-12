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
import java.util.ArrayList;
import java.util.List;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(ExamCategoryDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExamCategoryDTO> search(String searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
