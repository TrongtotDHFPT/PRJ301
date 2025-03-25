/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDAO;
import dao.ProductDAO;
import dto.CartDTO;
import dto.ProductDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static final ProductDAO pdao = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url = VIEW_PAGE;
        UserDTO user = (UserDTO) session.getAttribute("user");
        String action = request.getParameter("action");

        //===delete
        if ("delete".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            cdao.removeFromCart(user.getUser_id(), productId);

            List<CartDTO> listCarts = cdao.getCartByUserID(user.getUser_id());
            request.setAttribute("listCarts", listCarts);

            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
        //===delete
        try {
            //add
            if (user == null) {
                request.setAttribute("message", "Please Login!");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

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

                boolean isAdded = cdao.addToCart(user.getUser_id(), product_id, quantity);

                ProductDTO product = pdao.getProductById(product_id);
                request.setAttribute("product", product);
                List<ProductDTO> list_sameCategory = pdao.getProductByCategoryID(product.getCategory_id());
                request.setAttribute("list_sameCategory", list_sameCategory);

                if (isAdded) {
                    request.setAttribute("message", "Added to cart successfully!");
                } else {
                    request.setAttribute("message", "Failed to add to cart!");
                }
                url = VIEW_PAGE;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Internal server error!");
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); 
    }

}
