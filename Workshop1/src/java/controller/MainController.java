/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StartupProjectDAO;
import dao.UserDAO;
import dto.StartupProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public static final String LOGIN_PAGE = "login.jsp";
    private static final StartupProjectDAO spdao = new StartupProjectDAO();

    public UserDTO getUser(String strUsername) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readByUsername(strUsername);
        return user;
    }

    public boolean isValidLogin(String strUsername, String strPassword) {
        UserDTO user = getUser(strUsername);
        return user != null && (user.getPassword().equals(strPassword));
    }

    public boolean isValidStatus(String status) {
        return status != null && (status.trim().equalsIgnoreCase("Ideation") || status.trim().equalsIgnoreCase("Development")
                || status.trim().equalsIgnoreCase("Launch") || status.trim().equalsIgnoreCase("Scaling"));
    }

    public void processSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("txtsearchTerm");
        if (searchTerm == null) {
            searchTerm = "";
        }
        List<StartupProjectDTO> list = spdao.searchByTerm(searchTerm);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("list", list);
    }

    public String processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        //
        String strUsername = request.getParameter("txtUserID");
        String strPassword = request.getParameter("txtPassword");
        if (isValidLogin(strUsername, strPassword)) {
            UserDAO udao = new UserDAO();
            UserDTO user = udao.readByUsername(strUsername);
            request.getSession().setAttribute("user", user);
            processSearch(request, response);
            url = "search.jsp";
        } else {
            request.setAttribute("message", "Incorrect Username or Password!");
            url = "login.jsp";
        }
        return url;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = LOGIN_PAGE;
        try {
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    url = processLogin(request, response);
                } else if (action.equals("logout")) {
                    request.getSession().invalidate();
                    url = "login.jsp";
                } else if (action.equals("search")) {
                    processSearch(request, response);
                    url = "search.jsp";
                } else if (action.equals("update")) {
                    int project_id = Integer.parseInt(request.getParameter("project_id"));
                    StartupProjectDTO project = spdao.readByID(project_id);
                    System.out.println(project);
                    request.setAttribute("project", project);
                    url = "update.jsp";
                } else if (action.equals("updateStatus")) {
                    int project_id = Integer.parseInt(request.getParameter("project_id"));
                    String status = request.getParameter("status");

                    if (isValidStatus(status)) {
                        boolean isUpdated = spdao.updateStatusByID(project_id, status);
                        if (isUpdated) {
                            request.setAttribute("message", "Updated status successfully!");
                        } else {
                            request.setAttribute("message", "Updated status fail!");
                        }
                    } else {
                        request.setAttribute("message", "Invalid status!Status must be one of: Ideation, Development, Launch, Scaling.");
                    }

                    StartupProjectDTO project = spdao.readByID(project_id);
                    request.setAttribute("project", project);
                    request.setAttribute("status", status);
                    url = "update.jsp";
                } else if (action.equals("create")) {
                    String projectName = request.getParameter("txtProjectName");
                    String description = request.getParameter("txtDescription");
                    String status = request.getParameter("txtStatus");
                    String launchDate = request.getParameter("txtDate"); //kiểu "yyyy-MM-dd" 
                    
                    Date sqlDate = null;
                    
                    boolean check = false;
                    if (projectName == null || projectName.trim().equals("")) {
                        check = true;
                        request.setAttribute("txt_projectName_Error", "Project Name can't be empty!");
                    }
                    if (status == null || !isValidStatus(status) || status.trim().equals("")) {
                        check = true;
                        request.setAttribute("txt_statusInValid_Error", "Invalid status!Status must be one of: Ideation, Development, Launch, Scaling.");
                    }
                    if(launchDate == null || launchDate.trim().equals("")){
                        check = true;
                        request.setAttribute("txt_dateError", "Launch date cannot be empty!");
                    }else{
                        try {
                            LocalDate localDate = LocalDate.parse(launchDate);
                            sqlDate = Date.valueOf(localDate);
                        } catch (DateTimeParseException e) {
                            check = true;
                            request.setAttribute("txt_dateError", "Invalid date format! Please use yyyy-MM-dd.");
                        }
                    }

                    StartupProjectDTO project = new StartupProjectDTO(0, projectName, description, status, sqlDate);
                    if (check == false) {
                        spdao.create(project);
                        request.setAttribute("message", "Create Project Successfully!");
                        url = "search.jsp";
                    } else {
//                        request.setAttribute("project", project);
                        url = "projectForm.jsp";
                    }

                } else if (action.equals("abc")) {
                    String projectName = request.getParameter("txtProjectName");
                    String description = request.getParameter("txtDescription");
                    String status = request.getParameter("txtStatus");
                    String launchDate = request.getParameter("txtDate"); // Lấy giá trị từ request
                    Date sqlDate = null;
                     boolean check = false;
                    if (launchDate == null || launchDate.trim().isEmpty()) {
                        // Xử lý lỗi nếu launchDate rỗng hoặc null
                        check = true;
                        request.setAttribute("txt_dateError", "Launch date cannot be empty!");
                    } else {
                        try {
                            LocalDate localDate = LocalDate.parse(launchDate); // Phân tích chuỗi thành LocalDate
                            sqlDate = Date.valueOf(localDate); // Chuyển đổi thành java.sql.Date
                        } catch (DateTimeParseException e) {
                            // Xử lý lỗi nếu chuỗi không đúng định dạng
                            check = true;
                            request.setAttribute("txt_dateError", "Invalid date format! Please use yyyy-MM-dd.");
                        }
                    }
                    // Kiểm tra projectName
                    if (projectName == null || projectName.trim().isEmpty()) {
                        check = true;
                        request.setAttribute("txt_projectName_Error", "Project Name cannot be empty!");
                    }

                    // Kiểm tra status
                    if (!isValidStatus(status) || status.trim().isEmpty()) {
                        check = true;
                        request.setAttribute("txt_statusInValid_Error", "Invalid status! Status must be one of: Ideation, Development, Launch, Scaling.");
                    }
                    // Nếu không có lỗi, tạo dự án
                    if (!check) {
                        StartupProjectDTO project = new StartupProjectDTO(0, projectName, description, status, sqlDate);
                        spdao.create(project);
                        request.setAttribute("message", "Create Project Successfully!");
                        url = "search.jsp";
                    } else {
                        url = "projectForm.jsp"; // Chuyển hướng về trang tạo dự án nếu có lỗi
                    }
                }
            }
        } catch (Exception e) {
            log("Error at MainController :" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    public StartupProjectDTO getProjectByID(int project_id) {
        StartupProjectDAO spdao = new StartupProjectDAO();
        StartupProjectDTO project = spdao.readByID(project_id);
        return project;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
