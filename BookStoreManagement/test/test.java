///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
//import static controller.ViewCartServlet.cartDAO;
//import dao.CartDAO;
//import dao.OrderDAO;
//import dao.ProductDAO;
//import dto.CartDTO;
//import dto.OrderDTO;
//import dto.ProductDTO;
//import dto.UserDTO;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// *
// * @author trong
// */
//public class CheckOutServlet extends HttpServlet {
//
//    ProductDAO pdao = new ProductDAO();
//    OrderDAO orderdao = new OrderDAO();
//    CartDAO cartdao = new CartDAO();
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        UserDTO user = (UserDTO) session.getAttribute("user");
//
//        if (user == null) {
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//            return;
//        }
//
//        String[] selectedProducts_id = request.getParameterValues("selectedProducts");
//        String[] quantities = request.getParameterValues("quantity");
//
//        if (selectedProducts_id == null || selectedProducts_id.length == 0) {
//            List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
//            request.setAttribute("listCarts", listCarts);
//            request.setAttribute("message", "Bạn chưa chọn sản phẩm nào để thanh toán.");
//            request.getRequestDispatcher("cart.jsp").forward(request, response);
//            return;
//        }
//
//        List<CartDTO> selectedProducts = new ArrayList<>();
//        boolean outStock = false;
//        StringBuilder stockMessage = new StringBuilder();
//
//        for (int i = 0; i < selectedProducts_id.length; i++) {
//
//            int product_id = Integer.parseInt(selectedProducts_id[i]);
//            int newQuantity = Integer.parseInt(quantities[i]);
//
//            CartDTO cartProduct = cartdao.getCart(user.getUser_id(), product_id);
//            ProductDTO product = pdao.getProductById(product_id);
//
//            if (cartProduct == null || product == null) {
//                outStock = true;
//                stockMessage.append("Sản phẩm không tồn tại.<br>");
//            } else {
//                 if (newQuantity > product.getStock()) {
//                    outStock = true;
//                    stockMessage.append("Sản phẩm ").append(product.getTitle())
//                            .append(" chỉ còn ").append(product.getStock()).append(" sản phẩm!<br>");
//                } else {
//                    cartdao.updateCartQuantity(user.getUser_id(), product_id, newQuantity);
//                    cartProduct.setQuantity(newQuantity);
//                    selectedProducts.add(cartProduct);
//                }
//            }
//        }
//
//        if (outStock) {
//            List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
//            request.setAttribute("listCarts", listCarts);
//            request.setAttribute("message", stockMessage.toString());
//            request.getRequestDispatcher("cart.jsp").forward(request, response);
//            return;
//        }
//
//        double totalPrice = 0;
//        for (CartDTO cart : selectedProducts) {
//            totalPrice += cart.getQuantity() * cart.getProduct().getPrice();
//        }
//
//        OrderDTO newOrder = new OrderDTO(0, user.getUser_id(), totalPrice, "Pending");
//        boolean isOrderCreated = orderdao.createOrder(newOrder);
//
//        if (isOrderCreated) {
//            for (CartDTO item : selectedProducts) {
//                pdao.updateStock(item.getProduct().getProduct_id(), item.getQuantity()); 
//            }
//            for (CartDTO item : selectedProducts) {
//                cartDAO.removeFromCart(user.getUser_id(), item.getProduct().getProduct_id()); 
//            }
//            request.setAttribute("message", "Thanh toán thành công!");
//        } else {
//            request.setAttribute("message", "Thanh toán thất bại!");
//        }
//        request.setAttribute("totalPrice", totalPrice);
//        List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
//        request.setAttribute("listCarts", listCarts);
//        request.getRequestDispatcher("cart.jsp").forward(request, response);
//    }
//}


//last-product {
//    background: linear-gradient(to right, #4ca1af, #2c3e50);
//    color: black;
//    padding: 20px;
//    border-radius: 8px;
//    margin-bottom: 20px;
//    box-shadow: 0px 0px 15px rgba(76, 161, 175, 0.8); /* Ánh sáng xanh */
//    text-align: center;
//    transition: 0.3s ease-in-out;
//}
//
//.last-product:hover {
//    box-shadow: 0px 0px 25px rgba(76, 161, 175, 1); /* Hiệu ứng sáng hơn khi hover */
//}
//
//.last-product .product-item {
//    display: flex;
//    align-items: center;
//    justify-content: center;
//    gap: 20px;
//}
//.last-product {
//    position: relative;
//}
//
//.last-product::before {
//    content: " New book 🔥";
//    position: absolute;
//    top: -10px;
//    left: -10px;
//    background: indianred;
//    color: white;
//    font-size: 14px;
//    font-weight: bold;
//    padding: 5px 10px;
//    border-radius: 5px;
//    box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
//}
//
//
//.last-product img {
//    width: 140px;
//    height: 170px;
//    object-fit: cover;
//    border-radius: 5px;
//    border: 3px solid white;
//    transition: transform 0.3s ease;
//}
//
//.last-product img:hover {
//    transform: scale(1.1); /* Phóng to khi di chuột vào */
//}
//
//.last-product h3 {
//    font-size: 22px;
//    font-weight: bold;
//}
//
//.last-product a {
//    color: #f1c40f;
//    font-weight: bold;
//    text-decoration: none;
//    transition: all 0.3s ease;
//}
//
//.last-product a:hover {
//    color: #e67e22;
//    text-decoration: underline;
//}