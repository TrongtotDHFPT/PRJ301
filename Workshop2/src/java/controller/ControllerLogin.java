/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ExamCategoryDAO;
import dao.ExamDAO;
import dao.UserDAO;
import dto.ExamCategoryDTO;
import dto.ExamDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AuthUtils;

/**
 *
 * @author trong
 */
@WebServlet(name = "ControllerLogin", urlPatterns = {"/ControllerLogin"})
public class ControllerLogin extends HttpServlet {

    public static final String LOGIN_PAGE = "login.jsp";
    public static final String SEARCH_PAGE = "search.jsp";

    public static final ExamCategoryDAO exCateDAO = new ExamCategoryDAO();
    public static final ExamDAO examDAO = new ExamDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;

        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();

            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user == null && !action.equals("login")) {
                url = LOGIN_PAGE;
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            if (action == null) {
                url = LOGIN_PAGE;
            } else if (action.equals("login")) {
                String strUsername = request.getParameter("txtUsername");
                String strPassword = request.getParameter("txtPassword");

                if (AuthUtils.isValidLogin(strUsername, strPassword)) {
                    user = AuthUtils.getUser(strUsername);
                    session.setAttribute("user", user);

                    List<ExamCategoryDTO> list = exCateDAO.readAll();
                    request.setAttribute("list", list);
                    url = SEARCH_PAGE;
                } else {
                    request.setAttribute("message", "Incorrect Username or Password!");
                    url = LOGIN_PAGE;
                }
            } else if (action.equals("logout")) {
                session.invalidate();
                url = LOGIN_PAGE;
            } else if (action.equals("filter")) {

                String category_id = request.getParameter("category_id");
                System.out.println(category_id);
                if (category_id != null && !category_id.isEmpty()) {
                    int category_idInt = Integer.parseInt(category_id);
                    List<ExamDTO> listExamDTO = ExamDAO.getExamCategoryByID(category_idInt);
                    String category_name = exCateDAO.getCategoryNameById(category_idInt);
                    request.setAttribute("listExamDTO", listExamDTO);
                    request.setAttribute("category_name", category_name);
                }else{
                    request.setAttribute("message_Filter", "Please choose Category Name!");
                }
                List<ExamCategoryDTO> list = exCateDAO.readAll();
                request.setAttribute("list", list);
                url = SEARCH_PAGE;
            } else if (action.equals("goToCreateExam")) {
                List<ExamCategoryDTO> list = exCateDAO.readAll(); // Lấy tất cả category từ DB
                request.setAttribute("list", list); // Truyền qua JSP
                url = "examForm.jsp"; // Mở form tạo đề thi
            } else if (action.equals("createExam")) {
                String exam_title = request.getParameter("exam_title");
                String subject = request.getParameter("subject");
                int category_id = Integer.parseInt(request.getParameter("category_id"));
                int total_marks = Integer.parseInt(request.getParameter("total_marks"));
                int duration = Integer.parseInt(request.getParameter("duration"));

                ExamDTO newExam = new ExamDTO(0, exam_title, subject, category_id, total_marks, duration);

                boolean checkInsert = ExamDAO.insertExam(newExam);

                if (checkInsert) {
                    request.setAttribute("message", "Exam created successfully!");
                } else {
                    request.setAttribute("message", "Failed to create exam.");
                }

                // Load lại list category để hiển thị form
                List<ExamCategoryDTO> list = exCateDAO.readAll();
                request.setAttribute("list", list);
                url = "search.jsp"; // Trở về lại trang form để nhập tiếp
            }

        } catch (Exception e) {
            log("Error at ControllerLogin : " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
