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

    protected String processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;

        HttpSession session = request.getSession();
        String strUsername = request.getParameter("txtUsername");
        String strPassword = request.getParameter("txtPassword");
        if (AuthUtils.isValidLogin(strUsername, strPassword)) {
            UserDTO user = AuthUtils.getUser(strUsername);
            session.setAttribute("user", user);

            List<ExamCategoryDTO> list = exCateDAO.readAll();
            request.setAttribute("list", list);
            url = SEARCH_PAGE;
        } else {
            request.setAttribute("message", "Incorrect Username or Password!");
            url = LOGIN_PAGE;
        }
        return url;
    }

    protected String processSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = SEARCH_PAGE;
        List<ExamCategoryDTO> list = exCateDAO.readAll();
        request.setAttribute("list", list);
        return url;
    }

    protected String processCreateExam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = SEARCH_PAGE;
        String exam_title = request.getParameter("exam_title");
        String subject = request.getParameter("subject");
        String strcategory_id = request.getParameter("category_id");
        String strtotal_marks = request.getParameter("total_marks");
        String strduration = request.getParameter("duration");

        boolean check = false;
        if (exam_title == null || exam_title.trim().equals("")) {
            check = true;
            request.setAttribute("Error_exam_title", "Exam Title can't empty!");
        }
        if (subject == null || subject.trim().equals("")) {
            check = true;
            request.setAttribute("Error_subject", "Subject can't empty!");
        }

        int category_id = 0;
        if (strcategory_id == null || strcategory_id.trim().isEmpty()) {
            check = true;
            request.setAttribute("Error_category_id", "Category can't be empty!");
        } else {
            try {
                category_id = Integer.parseInt(strcategory_id);
            } catch (NumberFormatException e) {
                check = true;
                request.setAttribute("Error_category_id", "Invalid Category!");
            }
        }

        int total_marks = 0;
        if (strtotal_marks == null || strtotal_marks.trim().isEmpty()) {
            check = true;
            request.setAttribute("Error_total_marks", "Total Marks can't be empty!");
        } else {
            try {
                total_marks = Integer.parseInt(strtotal_marks);
                if (total_marks <= 0) {
                    check = true;
                    request.setAttribute("Error_total_marks", "Total Marks must be greater than 0!");
                }
            } catch (NumberFormatException e) {
                check = true;
                request.setAttribute("Error_total_marks", "Invalid Total Marks!");
            }
        }

        int duration = 0;
        if (strduration == null || strduration.trim().isEmpty()) {
            check = true;
            request.setAttribute("Error_duration", "Duration can't be empty!");
        } else {
            try {
                duration = Integer.parseInt(strduration);
                if (duration <= 0) {
                    check = true;
                    request.setAttribute("Error_duration", "Duration must be greater than 0!");
                }
            } catch (NumberFormatException e) {
                check = true;
                request.setAttribute("Error_duration", "Invalid Duration!");
            }
        }

        ExamDTO exam = new ExamDTO(0, exam_title, subject, category_id, total_marks, duration);
        request.setAttribute("exam", exam);
        List<ExamCategoryDTO> list = exCateDAO.readAll();
        request.setAttribute("list", list);
        if (check) {
            url = "createExam.jsp";
        } else {
            boolean result = examDAO.create(exam);
            if (result) {
                request.setAttribute("message", "Create Exam Successfully!");
            } else {
                request.setAttribute("message", "Create Exam Fail!");
            }
            url = "createExam.jsp";
        }
        return url;
    }

    protected String processFilter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = SEARCH_PAGE;

        String category_id = request.getParameter("category_id");
        System.out.println(category_id);
        if (category_id != null && !category_id.isEmpty()) {
            int category_idInt = Integer.parseInt(category_id);
            List<ExamDTO> listExamDTO = ExamDAO.getExamCategoryByID(category_idInt);
            String category_name = exCateDAO.getCategoryNameById(category_idInt);
            request.setAttribute("listExamDTO", listExamDTO);
            request.setAttribute("category_name", category_name);
        } else {
            request.setAttribute("message_Filter", "Please choose Category Name!");
        }
        List<ExamCategoryDTO> list = exCateDAO.readAll();
        request.setAttribute("list", list);
        url = SEARCH_PAGE;
        return url;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); 
        response.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html; charset=UTF-8");
        
        String url = LOGIN_PAGE;

        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    url = processLogin(request, response);
                } else if (action.equals("search")) {
                    url = processSearch(request, response);
                } else if (action.equals("logout")) {
                    request.getSession().invalidate();
                    url = LOGIN_PAGE;
                } else if (action.equals("filter")) {
                    url = processFilter(request, response);
                } else if (action.equals("goToCreateExam")) {
                    List<ExamCategoryDTO> list = exCateDAO.readAll();
                    request.setAttribute("list", list);
                    url = "createExam.jsp";
                } else if (action.equals("createExam")) {
                    url = processCreateExam(request, response);
                }
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
