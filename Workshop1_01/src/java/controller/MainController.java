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
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private boolean isValidStatus(String status) {
    return status != null && (status.equals("Ideation") || status.equals("Development") || status.equals("Launch") || status.equals("Scaling"));
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
                    StartupProjectDAO spdao = new StartupProjectDAO();
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
                            request.setAttribute("message", "Status updated successfully!");
                        } else {
                            request.setAttribute("message", "Failed to update status!");
                        }
                    } else {
                        request.setAttribute("message", "Invalid status! Status must be one of: Ideation, Development, Launch, Scaling.");
                    }

                    StartupProjectDTO project = spdao.readByID(project_id);
                    request.setAttribute("project", project);
                    request.setAttribute("status", status);
                    url = "update.jsp";
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
