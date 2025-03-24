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
import utils.AuthUtils;

/**
 *
 * @author trong
 */
public class ManagerProductsServlet extends HttpServlet {

    public static final String MANAGER_PRODUCTS = "managerProducts.jsp";
    public static final String HOME = "home.jsp";
    public static final ProductDAO pdao = new ProductDAO();
    private static final int PRODUCTS_PER_PAGE = 10;

    private String processSearch(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        String url = MANAGER_PRODUCTS;
        List<ProductDTO> listProduct;
        String searchTerm = request.getParameter("searchTerm");

        try {
            if ("delete".equals(action)) {
                String strProduct_id = request.getParameter("product_id");
                int product_id = 0;
                try {
                    product_id = Integer.parseInt(strProduct_id);
                } catch (NumberFormatException e) {
                    log("NumberFormatException at ManagerProductServlet: " + e.toString());
                }
                pdao.delete(product_id);
            }

            if ("search".equals(action)) {
                listProduct = pdao.search(searchTerm);

            } else {
                listProduct = pdao.readAll();
            }
            if (listProduct == null) {
                request.setAttribute("message", "Không tìm thấy dữ liệu");
            }
            String pageParameter = request.getParameter("page");
            int currentPage = 1;
            if (pageParameter != null && !pageParameter.isEmpty()) {
                currentPage = Integer.parseInt(pageParameter);
            }
            int totalProducts = listProduct.size();
            int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

            int startIndex = (currentPage - 1) * PRODUCTS_PER_PAGE;
            int endIndex = Math.min(startIndex + PRODUCTS_PER_PAGE, totalProducts);
            List<ProductDTO> list = listProduct.subList(startIndex, endIndex);
            
            request.setAttribute("list", list);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("searchTerm", searchTerm);

        } catch (Exception e) {
            log("Exception at processSearch: " + e.toString());
        }

        return url;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String action = request.getParameter("action");
        String url = processSearch(request, response, action);
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
