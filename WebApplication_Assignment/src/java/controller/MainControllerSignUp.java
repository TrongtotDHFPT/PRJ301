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
@WebServlet(name = "MainControllerSignUp", urlPatterns = {"/MainControllerSignUp"})
public class MainControllerSignUp extends HttpServlet {

    public static final String LOGIN_PAGE = "login.jsp";
    AccountDAO acDAO = new AccountDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = "login.jsp";
            } else {
                if (action.equals("signup")) {
                    String strName = request.getParameter("txtName");
                    String strUsername = request.getParameter("txtUserName");
                    String strPassword = request.getParameter("txtPassword");
                    String strConfirmPassword = request.getParameter("txtConfirmPassword");

                    boolean hasError = false; 
                    
                    if (strName == null || strName.trim().isEmpty()) {
                        request.setAttribute("errorName", "Name không được để trống");
                        hasError = true;
                    }
                    
                    if (strUsername == null || strUsername.trim().isEmpty()) {
                        request.setAttribute("errorUsername", "Username không được để trống");
                        hasError = true;
                    } else if (!strUsername.matches("^[a-zA-Z0-9_]{5,20}$")) {
                        request.setAttribute("errorUsername", "Username chỉ được chứa chữ, số và dấu gạch dưới, độ dài từ 5-20 ký tự");
                        hasError = true;
                    }

                    if (strPassword == null || strPassword.trim().isEmpty()) {
                        request.setAttribute("errorPassword", "Mật khẩu không được để trống");
                        hasError = true;
                    } else if (strPassword.length() < 8) {
                        request.setAttribute("errorPassword", "Mật khẩu phải có ít nhất 8 ký tự");
                        hasError = true;
                    }

                    if (strConfirmPassword == null || !strConfirmPassword.equals(strPassword)) {
                        request.setAttribute("errorConfirmPassword", "Confirm Password không khớp với Password");
                        hasError = true;
                    }

                    if (hasError) {
                        request.setAttribute("strName", strName);
                        request.setAttribute("strUsername", strUsername);
                        url = "signup.jsp";
                    } else {
                        if (AuthUtils.isNotUsernameAvailable(strUsername)) { //chưa tồn tại
                            AccountDTO account = new AccountDTO(0, strUsername,strName, strPassword, "USER");  
                            boolean isCreated = acDAO.create(account);
                            if (isCreated) {
                                request.setAttribute("message", "Sign up successfully!");
                            } else {
                                request.setAttribute("message", "Có lỗi xảy ra khi tạo tài khoản, vui lòng thử lại!");
                            }
                            url = "signup.jsp";
                        } else {
                            request.setAttribute("strName", strName);
                            request.setAttribute("message", "Username đã tồn tại, vui lòng chọn username khác!");
                            url = "signup.jsp";
                        }
                    }
                }

            }
        } catch (Exception e) {
        } finally {
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
