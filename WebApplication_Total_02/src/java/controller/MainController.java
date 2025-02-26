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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    public static final String LOGIN_PAGE = "login.jsp";
    public UserDTO getUser(String strUserID){
        UserDAO udao  = new UserDAO();
        UserDTO user = udao.readByID(strUserID);
        return user;
    }
    
    public boolean isValidLogin(String strUserID , String strPassword){
        UserDTO user = getUser(strUserID);
        return user != null &&(user.getPassword().equals(strPassword));
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = LOGIN_PAGE;
        try {
            if(action == null){
                url = LOGIN_PAGE;
            }else{
                if(action.equals("login")){
                    String strUserID = request.getParameter("txtUserID");
                    String strPassword = request.getParameter("txtPassword");
                    if(isValidLogin(strUserID, strPassword)){
                        UserDTO user = getUser(strUserID);
                        request.getSession().setAttribute("user", user);
                        url = "search.jsp";
                    }else{
                        url = "login.jsp";
                        request.setAttribute("message", "Incorrect UserID or Password");
                    }
                }else if(action.equals("logout")){
                    request.getSession().invalidate();
                     url="login.jsp";
                }else if(action.equals("search")){
                    String searchTerm = request.getParameter("searchTerm");
                    BookDAO bdao = new BookDAO();
                    List<BookDTO> books = bdao.searchByTitle(searchTerm);
                    request.setAttribute("books", books);
                    request.setAttribute("searchTerm", searchTerm);
                     url ="search.jsp";
                }else if(action.equals("delete")){
                    String id = request.getParameter("id");
                    BookDAO bdao = new BookDAO();
                    bdao.setQuantityToZero(id);
                    
                    String searchTerm = request.getParameter("searchTerm");
                    List<BookDTO> books = bdao.searchByTitle(searchTerm);
                    request.setAttribute("books", books);
                    request.setAttribute("searchTerm", searchTerm);
                     url ="search.jsp";
                }
            }
        } catch (Exception e) {
            log("Error at MainController: "+e.toString());
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
