/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
import dao.UserDAO;
import dto.ProductDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
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
    public static final String HOME_PAGE = "home.jsp";
    public static final UserDAO udao = new UserDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String strUsername = request.getParameter("txtUsername");
            String strPassword = request.getParameter("txtPassword");
            System.out.println("Username: " + strUsername);
            System.out.println("Password: " + strPassword);

            if (AuthUtils.isValidLoggin(strUsername, strPassword)) {
                UserDTO user = AuthUtils.getUser(strUsername);
                ProductDAO pdao = new ProductDAO();
                List<ProductDTO> list = pdao.readAll();
                request.getSession().setAttribute("user", user);
                request.setAttribute("list", list);
                System.out.println(user);
                url = HOME_PAGE;
            } else {
                request.setAttribute("message", "Incorrect Username or Password!");
                url = LOGIN_PAGE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
