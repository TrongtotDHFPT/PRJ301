/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
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
public class RegisterServlet extends HttpServlet {

    public static final String RESGISTER_PAGE = "register.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String strName = request.getParameter("name");
            String strUsername = request.getParameter("username");
            String strPassword = request.getParameter("password");
            String strEmail = request.getParameter("email");
            String strPhone = request.getParameter("phone");
            String strAddress = request.getParameter("address");
            //chưa bắt lỗi

            boolean hasError = false;
            if (strName == null || strName.trim().isEmpty()) {
                request.setAttribute("Error_strName", "Name cannot be empty!");
                hasError = true;
            }

            if (strUsername == null || strUsername.trim().isEmpty()) {
                request.setAttribute("Error_strUsername", "Username cannot be empty!");
                hasError = true;
            } else if (AuthUtils.isExist(strUsername)) { // Check username đã tồn tại
                request.setAttribute("Error_strUsername", "Username existed!");
                hasError = true;
            }

            if (strPassword == null || strPassword.trim().isEmpty()) {
                request.setAttribute("Error_strPassword", "Password cannot be empty!");
                hasError = true;
            } else if (strPassword.length() < 6) {
                request.setAttribute("Error_strPassword", "Password must be at least 6 characters!");
                hasError = true;
            }

            // Validate Email
            if (strPhone == null || strPhone.trim().isEmpty()) {
                request.setAttribute("Error_strPhone", "Phone cannot be empty!");
                hasError = true;
            } else if (!strPhone.matches("\\d{10,11}")) { // Số điện thoại 10-11 số
                request.setAttribute("Error_strPhone", "Invalid phone number!");
                hasError = true;
            }

            // Validate Address
            if (strAddress == null || strAddress.trim().isEmpty()) {
                request.setAttribute("Error_strAddress", "Address cannot be empty!");
                hasError = true;
            }
            if (hasError) {
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            UserDTO user = new UserDTO(0, strName, strUsername, strPassword, "", strEmail, strPhone, strAddress);
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
        doPost(request, response); //To change body of generated methods, choose Tools | Templates.
    }

}
