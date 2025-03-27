/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.ViewCartServlet.cartDAO;
import dao.CartDAO;
import dao.ProductDAO;
import dto.CartDTO;
import dto.ProductDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
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
public class CheckOutServlet extends HttpServlet {

    private final ProductDAO pdao = new ProductDAO();
    private final CartDAO cartdao = new CartDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String[] selectedProducts_id = request.getParameterValues("selectedProducts");
        String[] quantities = request.getParameterValues("quantity");

        if (selectedProducts_id == null || selectedProducts_id.length == 0 || quantities == null || quantities.length == 0) {
            request.setAttribute("message", "Bạn chưa chọn sản phẩm nào để thanh toán.");
            List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
            request.setAttribute("listCarts", listCarts);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        }

        List<CartDTO> selectedProducts = new ArrayList<>();
        double totalPrice = 0;
        for (int i = 0; i < selectedProducts_id.length; i++) {
            int product_id = Integer.parseInt(selectedProducts_id[i]);
            int newQuantity = Integer.parseInt(quantities[i]);

            ProductDTO product = pdao.getProductById(product_id);
            if (newQuantity > product.getStock()|| product == null ) {
                request.setAttribute("message", "Sản phẩm " + (product != null ? product.getTitle() : "") + " không đủ số lượng.");
                List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
                request.setAttribute("listCarts", listCarts);
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
            }

            CartDTO cartItem = cartdao.getCart(user.getUser_id(), product_id);
            if (cartItem != null) {
                cartItem.setQuantity(newQuantity);
                selectedProducts.add(cartItem);
                totalPrice += newQuantity * product.getPrice();
            }
        }

        session.setAttribute("selectedProducts", selectedProducts);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("user", user);
        request.getRequestDispatcher("checkOut.jsp").forward(request, response);
    }

}
