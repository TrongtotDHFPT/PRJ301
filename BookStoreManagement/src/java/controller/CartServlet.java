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

    public static final String CART_PAGE = "cart.jsp";
    public static final String HOME_PAGE = "home.jsp";
    public static final String VIEW_PAGE = "viewDetail.jsp";
    public static final CartDAO cdao = new CartDAO();
    public static final ProductDAO pdao = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = VIEW_PAGE;
        HttpSession session = request.getSession();

        try {
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user == null) {
                request.setAttribute("message", "Please Login!");
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            String productIdParam = request.getParameter("product_id");
            String stockParam = request.getParameter("quantity");

            if (productIdParam == null || stockParam == null) {
                request.setAttribute("message", "Invalid product data!");
                url = VIEW_PAGE;
//                request.getRequestDispatcher(VIEW_PAGE).forward(request, response);
                return;
            }

            int product_id, stock;
            try {
                product_id = Integer.parseInt(productIdParam);
                stock = Integer.parseInt(stockParam);

                if (stock <= 0) {
                    request.setAttribute("message", "Quantity must be greater than 0!");
                    url = VIEW_PAGE;
//                    request.getRequestDispatcher(VIEW_PAGE).forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid number format!");
//                request.getRequestDispatcher(VIEW_PAGE).forward(request, response);
                url = VIEW_PAGE;
                return;
            }

            List<CartDTO> carts = (List<CartDTO>) session.getAttribute("carts");
            if (carts == null) {
                carts = new ArrayList<>();
                session.setAttribute("carts", carts);
            }

            CartDTO cart = null;
            for (CartDTO c : carts) {
                if (c.getProduct_id() == product_id) {
                    cart = c;
                    break;
                }
            }

            if (cart == null) {
                Date addedAt = Date.valueOf(LocalDate.now());
                ProductDTO product = pdao.readByID(product_id);
                carts.add(new CartDTO(0, user.getUser_id(), product.getProduct_id(), stock, addedAt));
                request.setAttribute("message", "Add successfully!");
            } else {
                cart.setQuantity(cart.getQuantity() + stock);
                request.setAttribute("message", "Quantity updated successfully!");
            }
            session.setAttribute("carts", carts);
            System.out.println("Carts size: " + carts.size());
            for (CartDTO c : carts) {
                System.out.println("Product ID: " + c.getProduct_id() + ", Quantity: " + c.getQuantity());
            }
            System.out.println("Session ID: " + session.getId());

            url = CART_PAGE;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

}
