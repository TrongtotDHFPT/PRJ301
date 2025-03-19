/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.DetailServlet.pdao;
import dao.CartDAO;
import dao.ProductDAO;
import dto.CartDTO;
import dto.ProductDTO;
import dto.UserDTO;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trong
 */
public class CartServlet extends HttpServlet {

    public static final String VIEW_PAGE = "viewDetail.jsp";
    public static final CartDAO cdao = new CartDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url = VIEW_PAGE;

        try {
            // Kiểm tra user đã đăng nhập chưa
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user == null) {
                request.setAttribute("message", "Please Login!");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            // Lấy thông tin từ request
            String productIdParam = request.getParameter("product_id");
            String quantityParam = request.getParameter("quantity");

            if (productIdParam == null || quantityParam == null) {
                request.setAttribute("message", "Invalid product data!");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            try {
                int product_id = Integer.parseInt(productIdParam);
                int quantity = Integer.parseInt(quantityParam);

                if (quantity <= 0) {
                    request.setAttribute("message", "Quantity must be greater than 0!");
                    request.getRequestDispatcher(url).forward(request, response);
                    return;
                }

                boolean isAdded = cdao.addToCart(user.getUser_id(), product_id, quantity);
                ProductDAO pdao = new ProductDAO();
                ProductDTO product = pdao.readByID(product_id);
                request.setAttribute("product", product);
                List<ProductDTO> list_sameCategory = pdao.searchByCategoryID(product.getCategory_id());
                request.setAttribute("list_sameCategory", list_sameCategory);
                
                
                if (isAdded) {
                    request.setAttribute("message", "Added to cart successfully!");
                } else {
                    request.setAttribute("message", "Failed to add to cart!");
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Internal server error!");
        }

        // 🔹 **Fix: Luôn forward về JSP để hiển thị message**
        request.getRequestDispatcher(url).forward(request, response);
    }
}
