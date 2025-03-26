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
import javax.servlet.http.HttpSession;

/**
 *
 * @author trong
 */
public class AccountServlet extends HttpServlet {

    UserDAO udao = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("user"); 
            if (user == null) {
                response.sendRedirect("login.jsp");  
                return;
            }

            String strName = request.getParameter("name");
            String strPhone = request.getParameter("phone");
            String strAddress = request.getParameter("address");
            String strEmail = request.getParameter("email");
            boolean hasError = false;

            if (strName == null || strName.trim().isEmpty()) {
                request.setAttribute("Error_strName", "Name cannot be empty!");
                hasError = true;
            }

            if (strPhone == null || strPhone.trim().isEmpty()) {
                request.setAttribute("Error_strPhone", "Phone cannot be empty!");
                hasError = true;
            } else if (!Pattern.matches("\\d{10,11}", strPhone)) {
                request.setAttribute("Error_strPhone", "Invalid phone number! Must be 10-11 digits.");
                hasError = true;
            }

            if (strAddress == null || strAddress.trim().isEmpty()) {
                request.setAttribute("Error_strAddress", "Address cannot be empty!");
                hasError = true;
            }
            
            if (strEmail == null || strEmail.trim().isEmpty()) {
                request.setAttribute("Error_strEmail", "Email cannot be empty!");
                hasError = true;
            }else if (!Pattern.matches("^[A-Za-z0-9+_.-]+@gmail\\.com$", strEmail)) {
                request.setAttribute("Error_strEmail", "Email must be in the format 'xxxxx@gmail.com'!");
                hasError = true;
            }

            if (hasError) {
                request.getRequestDispatcher("account.jsp").forward(request, response);
                return;
            }

            UserDAO userDao = new UserDAO();
            boolean isUpdated = userDao.updateUser(user.getUser_id(), strName, strPhone, strAddress,strEmail);

            if (isUpdated) {
//                user.setName(strName);
//                user.setPhone(strPhone);
//                user.setAddress(strAddress);
//                user.setEmail(strEmail);
                user = udao.readByID(user.getUser_id());
                session.setAttribute("user", user); 
                request.setAttribute("message", "Update successful!");
            } else {
                request.setAttribute("message", "Update failed!");
            }

        } catch (Exception e) {
            log("Error at AccountServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher("account.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
