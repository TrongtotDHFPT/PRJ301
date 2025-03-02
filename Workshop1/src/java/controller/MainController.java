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

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("txtsearchTerm");
        if (searchTerm == null) {
            searchTerm = "";
        }
        List<StartupProjectDTO> list = spdao.searchByTerm(searchTerm);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("list", list);
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
                    String strUsername = request.getParameter("txtUserID");
                    String strPassword = request.getParameter("txtPassword");
                    if (isValidLogin(strUsername, strPassword)) {
                        UserDAO udao = new UserDAO();
                        UserDTO user = udao.readByUsername(strUsername);
                        request.getSession().setAttribute("user", user);
                        search(request, response);
                        url = "search.jsp";
                    } else {
                        request.setAttribute("message", "Incorrect Username or Password!");
                        url = "login.jsp";
                    }
                } else if (action.equals("logout")) {
                    request.getSession().invalidate();
                    url = "login.jsp";
                } else if (action.equals("search")) {
                    search(request, response);
                    url = "search.jsp";
                } else if (action.equals("create")) {
                    String project_name = request.getParameter("txtProjectName");
                    String description = request.getParameter("txtDescription");
                    String status = request.getParameter("txtStatus");
                    String txtDate = request.getParameter("txtDate");

//                    System.out.println("Action hiện tại:"+action);
                    //Chuyển đổi String thành java.sql.Date
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng đúng
                    java.util.Date utilDate = sdf.parse(txtDate);
                    Date estimated_launch = new Date(utilDate.getTime()); // Chuyển sang java.sql.Date
                    StartupProjectDTO spdto = new StartupProjectDTO(project_name, description, status, estimated_launch);
                    spdao.create(spdto);
                    boolean result = spdao.create(spdto);
                    System.out.println("✅ Kết quả create(): " + result);
                    if (!result) {
                        System.out.println("❌ Lỗi khi tạo project! Kiểm tra SQL.");
                        url = "search.jsp"; // Redirect đến trang lỗi thay vì login
                    }

                    url = "search.jsp";
                }
            }
        } catch (Exception e) {
            log("Error at MainController :" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

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
