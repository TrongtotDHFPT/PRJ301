/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDAO;
import dto.CategoryDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AuthUtils;

/**
 *
 * @author trong
 */
public class AddCategoryServlet extends HttpServlet {

    CategoryDAO catedao = new CategoryDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (AuthUtils.isAdmin(session)) {
            String action = request.getParameter("action");
            if (action == null) {
                List<CategoryDTO> cateList = catedao.readAll();
                request.setAttribute("cateList", cateList);
                request.getRequestDispatcher("category.jsp").forward(request, response);
                return;
            } else {
                if (action.equals("addCate")) {
                    String category_name = request.getParameter("category_name");

                    if (category_name == null || category_name.trim().isEmpty()) {
                        List<CategoryDTO> cateList = catedao.readAll();
                        request.setAttribute("cateList", cateList);
                        request.setAttribute("message", "Category is not valid!");
                        request.getRequestDispatcher("category.jsp").forward(request, response);
                        return;
                    }

                    boolean isAdded = catedao.create(category_name);

                    if (isAdded) {
                        List<CategoryDTO> cateList = catedao.readAll();
                        request.setAttribute("cateList", cateList);
                        request.getRequestDispatcher("category.jsp").forward(request, response);
                        return;
                    } else {
                        request.setAttribute("category_name", category_name);
                        request.setAttribute("message", "Failed to add category!");
                        List<CategoryDTO> cateList = catedao.readAll();
                        request.setAttribute("cateList", cateList);
                        request.getRequestDispatcher("category.jsp").forward(request, response);
                    }
                }
            }
        }else{
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
