/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import dto.UserDTO;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.AuthUtils;
import utils.PasswordUtils;

/**
 *
 * @author trong
 */
public class RegisterServlet extends HttpServlet {

    public static final String RESGISTER_PAGE = "register.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            String strName = request.getParameter("name");
            String strUsername = request.getParameter("username");
            String strPassword = request.getParameter("password");
            String strEmail = request.getParameter("email");
            String strPhone = request.getParameter("phone");
            String strAddress = request.getParameter("address");
            //chưa bắt lỗi xong

            boolean hasError = false;
            if (strName == null || strName.trim().isEmpty()) {
                request.setAttribute("Error_strName", "Name cannot be empty!");
                hasError = true;
            }

            if (strUsername == null || strUsername.trim().isEmpty()) {
                request.setAttribute("Error_strUsername", "Username cannot be empty!");
                hasError = true;
            } else if (AuthUtils.isExist(strUsername)) {
                request.setAttribute("Error_strUsername", "Username existed!");
                hasError = true;
            }

            if (strPassword == null || strPassword.trim().isEmpty()) {
                request.setAttribute("Error_strPassword", "Password cannot be empty!");
                hasError = true;
            } else if (strPassword.length() < 8) {
                request.setAttribute("Error_strPassword", "Password must be at least 8 characters!");
                hasError = true;
            }

            if (strPhone == null || strPhone.trim().isEmpty()) {
                request.setAttribute("Error_strPhone", "Phone cannot be empty!");
                hasError = true;
            } else if (!strPhone.matches("\\d{10,11}")) {
                request.setAttribute("Error_strPhone", "Invalid phone number! Must be 10-11 digits.");
                hasError = true;
            }

            if (strEmail == null || strEmail.trim().isEmpty()) {
                request.setAttribute("Error_strEmail", "Email cannot be empty!");
                hasError = true;
            } else if (!Pattern.matches("^[A-Za-z0-9+_.-]+@gmail\\.com$", strEmail)) {
                request.setAttribute("Error_strEmail", "Email must be in the format 'xxxxx@gmail.com'!");
                hasError = true;
            }

            if (strAddress == null || strAddress.trim().isEmpty()) {
                request.setAttribute("Error_strAddress", "Address cannot be empty!");
                hasError = true;
            }
            if (hasError) {
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            String hashedPassword = PasswordUtils.hashPassword(strPassword);
            UserDTO user = new UserDTO(0, strName, strUsername, hashedPassword, "", strEmail, strPhone, strAddress);
            UserDAO udao = new UserDAO();
            boolean isInserted = udao.insertUser(user);

            if (isInserted) {
                request.setAttribute("message", "Register successfully!");
                request.setAttribute("user", user);
            } else {
                request.setAttribute("message", "Register Fail!");
                request.setAttribute("user", user);
            }
        } catch (Exception e) {
            log("Error at RegisterServlet :" + e.toString());
        } finally {
            request.getRequestDispatcher(RESGISTER_PAGE).forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
