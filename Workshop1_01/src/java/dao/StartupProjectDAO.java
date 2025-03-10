/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.StartupProjectDTO;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author trong
 */
public class StartupProjectDAO implements IDAO<StartupProjectDTO, Integer> {

    public boolean updateStatusByID(int project_id, String status) {
        String sql = "UPDATE [tblStartupProjects]  SET status = ?  WHERE Project_id =?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, project_id);
            int rs = ps.executeUpdate();
            return rs > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean create(StartupProjectDTO entity) {
        String sql = "INSERT INTO tblStartupProjects (Project_id,Project_name, Description, Status, Estimated_launch)"
                + " VALUES (?,?, ?, ?, ?)";
        try {
            Connection con = DBUtils.getConnection();
            String getMaxID = "SELECT MAX(Project_id) FROM tblStartupProjects";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getMaxID);
            int entity_project_id = 1;
            if (rs.next()) {
                entity_project_id = rs.getInt(1) + 1;//Lấy giá trị từ cột đầu tiên của ResultSet
            }
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, entity_project_id);
            ps.setString(2, entity.getProject_name());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getStatus());
            ps.setDate(5, entity.getEstimated_launch());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<StartupProjectDTO> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(StartupProjectDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StartupProjectDTO> search(String searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StartupProjectDTO readByID(Integer id) {
        String sql = "SELECT * FROM tblStartupProjects WHERE [Project_id] =? ";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                StartupProjectDTO spDTO = new StartupProjectDTO(
                        rs.getInt("Project_id"),
                        rs.getString("Project_name"), 
                        rs.getString("Description"), 
                        rs.getString("Status"), 
                        rs.getDate("Estimated_launch"));
                return spDTO;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<StartupProjectDTO> searchByTerm(String searchTerm) {
        String sql = "SELECT * FROM tblStartupProjects WHERE Project_name LIKE ?";
        List<StartupProjectDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StartupProjectDTO project = new StartupProjectDTO(
                        rs.getInt("Project_id"),
                        rs.getString("Project_name"),
                        rs.getString("description"),
                        rs.getString("Status"),
                        rs.getDate("Estimated_launch"));
                list.add(project);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }
}
