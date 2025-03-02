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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public static final String LOGIN_PAGE = "login.jsp";
    
    public UserDTO getUser(String strUsername){
        UserDAO udao = new  UserDAO();
        UserDTO user = udao.readByUsername(strUsername);
        return user;
    }
    public boolean isValidLogin(String strUsername , String strPassword){
        UserDTO user = getUser(strUsername);
        return user != null&& (user.getPassword().equals(strPassword)) ;
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
                if(action.equals("login")){
                    String strUsername = request.getParameter("txtUserID");
                    String strPassword = request.getParameter("txtPassword");
                    if(isValidLogin(strUsername, strPassword)){
                        UserDAO udao = new UserDAO();
                        UserDTO user  = udao.readByUsername(strUsername);
                        request.getSession().setAttribute("user", user);
                        url = "search.jsp";
                    }else{
                        request.setAttribute("message","Incorrect Username or Password!");
                        url="login.jsp";
                    }
                }else if(action.equals("logout")){
                       request.getSession().invalidate();
                       url ="login.jsp";
                }else if(action.equals("search")){
                    StartupProjectDAO spdao = new StartupProjectDAO();
                    String searchTerm = request.getParameter("txtsearchTerm");
                    List<StartupProjectDTO> list = spdao.searchByTerm(searchTerm);
                    request.setAttribute("searchTerm", searchTerm);
                    request.setAttribute("list", list);
                    url = "search.jsp";
                }else if(action.equals("create")){
                    StartupProjectDAO spdao = new StartupProjectDAO();
                    String searchTerm = request.getParameter("txtsearchTerm");
                    List<StartupProjectDTO> list = spdao.searchByTerm(searchTerm);
                    request.setAttribute("searchTerm", searchTerm);
                    request.setAttribute("list", list);
                    url = "search.jsp";
                }
            }
        } catch (Exception e) {
            log("Error at MainController :"+e.toString());
        }finally{
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
