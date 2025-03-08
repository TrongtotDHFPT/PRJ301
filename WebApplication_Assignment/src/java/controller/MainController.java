/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dto.AccountDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.AuthUtils;

/**
 *
 * @author trong
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    public static final String LOGIN_PAGE = "login.jsp";
    AccountDAO acDAO = new AccountDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if(action == null){
                url = "login.jsp";
            }else{
                if(action.equals("login")){
                    String strUsername = request.getParameter("txtUserName");
                    String strPassword = request.getParameter("txtPassword");
                    if(AuthUtils.isValidLogin(strUsername, strPassword)){
                        AccountDTO account = acDAO.readByUsername(strUsername);
                        request.getSession().setAttribute("account", account);
                        url=  "home.jsp";
                    }else{
                        request.setAttribute("message","Incorrec UserName or PassWord.Please try again!");
                        url ="login.jsp";
                    }
                }else if(action.equals("signup")){
                    
                }else if(action.equals("logout")){
                    request.getSession().invalidate();
                    url = "login.jsp";
                }
            }
            
            
            
        } catch (Exception e) {
            log("Error At MainController :"+e.toString());
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
