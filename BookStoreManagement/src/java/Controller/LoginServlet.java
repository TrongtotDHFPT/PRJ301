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
public class LoginServlet extends HttpServlet {

    public static final String LOGIN_PAGE = "login.jsp";
    public static final String HOME_SERVLET = "home";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String strUsername = request.getParameter("txtUsername");
            String strPassword = request.getParameter("txtPassword");
            System.out.println("Username: " + strUsername);
            System.out.println("Password: " + strPassword);

            if (AuthUtils.isValidLogin(strUsername, strPassword)) {
                UserDTO user = AuthUtils.getUser(strUsername);
                request.getSession().setAttribute("user", user);
                System.out.println("User login thành công: " + user);
                response.sendRedirect(HOME_SERVLET);
                return ;
            } else {
                request.setAttribute("message", "Incorrect Username or Password!");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Internal error: " + e.getMessage());
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}