package controller;

import static controller.ViewCartServlet.cartDAO;
import dao.CartDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import dto.CartDTO;
import dto.OrderDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author trong
 */
public class ConfirmCheckOutServlet extends HttpServlet {

    private static final ProductDAO productDAO = new ProductDAO();
    private static final OrderDAO orderDAO = new OrderDAO();
    private static final CartDAO cartDAO = new CartDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String action = request.getParameter("action");
        if (action.equals("cancel")) {

            List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
            request.setAttribute("listCarts", listCarts);
            response.sendRedirect("viewCart");
            return;
        }

        List<CartDTO> selectedProducts = (List<CartDTO>) session.getAttribute("selectedProducts");
        
        for (CartDTO item : selectedProducts) {
            System.out.println(item);;
        }
        if (selectedProducts == null || selectedProducts.isEmpty()) {
            request.setAttribute("message", "Không có sản phẩm nào để thanh toán.");
            request.getRequestDispatcher("viewCart").forward(request, response);
            return;
        }

        double totalPrice = 0;
        for (CartDTO item : selectedProducts) {
            totalPrice += item.getQuantity() * item.getProduct().getPrice();
        }

        OrderDTO newOrder = new OrderDTO(0, user.getUser_id(), totalPrice, "PENDING");
        boolean isOrderCreated = orderDAO.createOrder(newOrder);

        if (isOrderCreated) {
            for (CartDTO item : selectedProducts) {
                productDAO.updateStock(item.getProduct().getProduct_id(), item.getQuantity());
                cartDAO.removeFromCart(user.getUser_id(), item.getProduct().getProduct_id());
            }
            session.removeAttribute("selectedProducts");
            response.sendRedirect("viewOrders.jsp"); 
        } else {
            request.setAttribute("message", "Thanh toán thất bại!");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
}
