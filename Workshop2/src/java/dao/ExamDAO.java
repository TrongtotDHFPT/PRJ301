/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ExamDTO;
import dto.UserDTO;
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
public class ExamDAO implements IDAO<ExamDTO, Integer> {

    @Override
    public List<ExamDTO> readAll() {
        String sql = "SELECT * FROM tblExams";
        List<ExamDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamDTO exam = new ExamDTO(
                        rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("Duration"));
                list.add(exam);
            }

        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public ExamDTO readByID(Integer id) {
        String sql = "SELECT * FROM tblExams WHERE [exam_id] = ? ";

        try {
            Connection con;
            con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamDTO exam = new ExamDTO(
                        rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("Duration"));
                return exam;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean create(ExamDTO entity) {
        String sql = "INSERT INTO tblExams"
                + "([exam_title], [subject],[category_id],[total_marks],[Duration]) "
                + "VALUES (?,?,?,?,?)";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getExam_title());
            ps.setString(2, entity.getSubject());
            ps.setInt(3, entity.getCategory_id());
            ps.setInt(4, entity.getTotal_marks());
            ps.setInt(5, entity.getDuration());
            int ketQua = ps.executeUpdate();
            return ketQua > 0;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM tblExams WHERE [exam_id] = ?";
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
    public List<ExamDTO> search(String searchTerm) {
        String sql = "SELECT * FROM tblExams WHERE"
                + " [exam_title] like ?"
                + " OR [subject] like ? ";
        List<ExamDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamDTO exam = new ExamDTO(
                        rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("Duration"));
                list.add(exam);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean update(ExamDTO entity) {
        String sql = "UPDATE tblExams SET"
                + "[exam_title] = ?,"
                + "[subject] = ?,"
                + "[category_id] = ?,"
                + "[total_marks] = ?,"
                + "[duration] = ?"
                + "WHERE [exam_id] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getExam_title());
            ps.setString(2, entity.getSubject());
            ps.setInt(3, entity.getCategory_id());
            ps.setInt(4, entity.getTotal_marks());
            ps.setInt(5, entity.getDuration());
            ps.setInt(6, entity.getCategory_id());
            int ketQua = ps.executeUpdate();
            return ketQua > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<ExamDTO> getExamCategoryByID(int category_id) {
        String sql = "  SELECT exam_id, exam_title, subject,category_id, total_marks, duration"
                + " FROM tblExams  "
                + "WHERE category_id = ? ";
        List<ExamDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamDTO exam = new ExamDTO(
                        rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("duration"));
                list.add(exam);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static boolean insertExam(ExamDTO exam) {
    String sql = "INSERT INTO tblExams (exam_title, subject, category_id, total_marks, duration) VALUES (?, ?, ?, ?, ?)";
    try (Connection con = DBUtils.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, exam.getExam_title());
        ps.setString(2, exam.getSubject());
        ps.setInt(3, exam.getCategory_id());
        ps.setInt(4, exam.getTotal_marks());
        ps.setInt(5, exam.getDuration());

        return ps.executeUpdate() > 0; // Trả về true nếu insert thành công

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


}
