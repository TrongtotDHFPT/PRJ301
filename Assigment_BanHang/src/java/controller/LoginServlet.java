/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.AuthUtils;

/**
 *
 * @author trong
 */

public class LoginServlet  extends HttpServlet{
    
    public static final String LOGIN_PAGE = "login.jsp";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        String action = request.getParameter("action");
        
        try {
            if(action == null){
                url = LOGIN_PAGE;
            }else{
                if(action.equals("login")){
                    String strUsername = request.getParameter("txtUsername");
                    String strPassword = request.getParameter("txtPassword");
                    if(AuthUtils.isValidLoggin(strUsername, strPassword)){
                        UserDTO user = AuthUtils.getUser(strUsername);
                        request.getSession().setAttribute("user", user);
                        url = "home.jsp";
                    }else{
                        request.setAttribute("message", "Incorrect Username or Password !");
                        url = LOGIN_PAGE;
                    }
                }
            }
            
        } catch (Exception e) {
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(LOGIN_PAGE); 
    }
}
