/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDAO;
import dao.UserDAO;
import dto.BookDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trong
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public static final String LOGIN_PAGE = "login.jsp";
    
    public UserDTO getUser(String strUserID){
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readByID(strUserID);
        return user;
    }
    public boolean isValidLogin(String strUserID , String strPassword){
        UserDTO user = getUser(strUserID);
        return user!= null && (user.getPasword().equals(strPassword));
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action  = request.getParameter("action");
        String url = LOGIN_PAGE;
        try {
            if(action == null){
                url = LOGIN_PAGE;
            }else{
                if(action.equals("login")){
                    String strUserID = request.getParameter("txtUserID");
                    String strPassword = request.getParameter("txtPassword");
                    if(isValidLogin(strUserID, strPassword)){
                        url = "search.jsp";
                        UserDTO user = getUser(strUserID);
                        request.getSession().setAttribute("user", user);
                    }else{
                        request.setAttribute("message", "Incorrect UserID or Password");
                        url = "login.jsp";
                    }
                }else if(action.equals("search")){
                    
                    BookDAO bdao = new BookDAO();
                    String searchTerm = request.getParameter("searchTerm");
                    List<BookDTO> list = bdao.readByTitle(searchTerm);
                    request.setAttribute("searchTerm", searchTerm);
                    request.setAttribute("list", list);
                    url = "search.jsp";
                }else if(action.equals("logout")){
                    request.getSession().invalidate();//hủy bỏ session
                    url = "login.jsp";
                }else if(action.equals("delete")){
                    //delete
                    BookDAO bdao = new BookDAO();
                    String id = request.getParameter("id");
                    bdao.updateQuantityToZero(id);
                    
                    //hiển thị lại bảng sau khi delete
                     
                    String searchTerm = request.getParameter("searchTerm");
                    List<BookDTO> list = bdao.readByTitle(searchTerm);
                    request.setAttribute("searchTerm", searchTerm);
                    request.setAttribute("list", list);
                    url = "search.jsp";
                }
            }
        } catch (Exception e) {
            System.out.println("Error at Main Controller :" +e.toString());
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
