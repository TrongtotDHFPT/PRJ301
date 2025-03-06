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
import javax.servlet.http.HttpSession;
import utils.AuthUtils;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public static final String LOGIN_PAGE = "login.jsp";
    private static final StartupProjectDAO spdao = new StartupProjectDAO();

    public String processSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "search.jsp";
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            String searchTerm = request.getParameter("txtsearchTerm");
            if (searchTerm == null) {
                searchTerm = "";
            }
            List<StartupProjectDTO> list = spdao.searchByTerm(searchTerm);
            request.setAttribute("searchTerm", searchTerm);
            request.setAttribute("list", list);
        }
        return url;
    }

    public String processUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "update.jsp";

        HttpSession session = request.getSession();
        if (AuthUtils.isFounder(session)) {
            int project_id = Integer.parseInt(request.getParameter("project_id"));
            StartupProjectDTO project = spdao.readByID(project_id);
            System.out.println(project);
            request.setAttribute("project", project);
        }

        return url;
    }

    public String processCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;

        HttpSession session = request.getSession();
        if (AuthUtils.isFounder(session)) {
            String projectName = request.getParameter("txtProjectName");
            String description = request.getParameter("txtDescription");
            String status = request.getParameter("txtStatus");
            String launchDate = request.getParameter("txtDate"); //kiểu "yyyy-MM-dd" 2025-02-23

            Date sqlDate = null;
            boolean check = false;
            if (projectName == null || projectName.trim().equals("")) {
                check = true;
                request.setAttribute("txt_projectName_Error", "Project Name can't be empty!");
            }
            if (status == null || !AuthUtils.isValidStatus(status) || status.trim().equals("")) {
                check = true;
                request.setAttribute("txt_statusInValid_Error", "Invalid status!Status must be one of: Ideation, Development, Launch, Scaling.");
            }
            if (launchDate == null || launchDate.trim().equals("")) {
                check = true;
                request.setAttribute("txt_dateError", "Launch date cannot be empty!");
            } else {
                try {
                    LocalDate localDate = LocalDate.parse(launchDate);
                    LocalDate today = LocalDate.now(); // Lấy ngày hiện tại
                    if (localDate.isBefore(today)) { // Kiểm tra nếu ngày nhập vào trước ngày hôm nay
                        check = true;
                        request.setAttribute("txt_dateError", "Launch date cannot be in the past!");
                    } else {
                        sqlDate = Date.valueOf(localDate);
                    }
                } catch (DateTimeParseException e) {
                    check = true;
                    request.setAttribute("txt_dateError", "Invalid date format! Please use yyyy-MM-dd.");
                }
            }

            StartupProjectDTO project = new StartupProjectDTO(0, projectName, description, status, sqlDate);
            if (check == false) {
                spdao.create(project);
                request.setAttribute("message", "Create Project Successfully!");
                url = "projectForm.jsp";
            } else {
                request.setAttribute("projectName", projectName);
                request.setAttribute("description", description);
                request.setAttribute("status", status);
                request.setAttribute("launchDate", launchDate);
                url = "projectForm.jsp";
            }
        }

        return url;
    }

    public String processUpdateStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;

        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            if (AuthUtils.isFounder(session)) {
                int project_id = Integer.parseInt(request.getParameter("project_id"));
                String status = request.getParameter("status");

                if (AuthUtils.isValidStatus(status)) {
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
            }
        }
        return url;
    }

    public String processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        //

        String strUsername = request.getParameter("txtUserID");
        String strPassword = request.getParameter("txtPassword");
        if (AuthUtils.isValidLogin(strUsername, strPassword)) {
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
                    url = processSearch(request, response);
                } else if (action.equals("update")) {
                    url = processUpdate(request, response);
                } else if (action.equals("updateStatus")) {
                    url = processUpdateStatus(request, response);
                } else if (action.equals("create")) {
                    url = processCreate(request, response);
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
