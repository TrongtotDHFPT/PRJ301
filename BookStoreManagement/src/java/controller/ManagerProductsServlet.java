/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
import dto.ProductDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trong
 */
public class ManagerProductsServlet extends HttpServlet {

    public static final String MANAGER_PRODUCTS = "managerProducts.jsp";
    public static final String HOME = "home.jsp";
    public static final ProductDAO pdao = new ProductDAO();
    private static final int PRODUCTS_PER_PAGE = 10;
    private static final List<ProductDTO> list = pdao.readAll();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            System.out.println("Session user is null, redirecting to login...");
            response.sendRedirect("login.jsp");
            return;
        }
        String url = MANAGER_PRODUCTS;

        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = HOME;
            } else {
                if (action.equals("null")) {
                    request.setAttribute("list", list);
                    url = MANAGER_PRODUCTS;
                } else if (action.equals("delete")) {
                    String strProduct_id = request.getParameter("product_id");
                    int product_id = 0;
                    try {
                        product_id = Integer.parseInt(strProduct_id);
                    } catch (NumberFormatException e) {
                        log("NumberFormatException at ManagerProductServlet :" + e.toString());
                    }
                    pdao.delete(product_id);
                    request.setAttribute("list", list);
                    url = MANAGER_PRODUCTS;
                }else if(action.equals("search")){
                    String searchTerm = request.getParameter("searchTerm");
                    if(searchTerm == null || searchTerm.trim().isEmpty()){
                        List<ProductDTO> list  = pdao.readAll();
                    }
                    List<ProductDTO> list = pdao.search(searchTerm);
                    request.setAttribute("list", list);
                    request.setAttribute("searchTerm", searchTerm);
                    url = MANAGER_PRODUCTS;
                }//add
            }

        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
