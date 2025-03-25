
import static controller.ViewCartServlet.cartDAO;
import dao.CartDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import dto.CartDTO;
import dto.OrderDTO;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trong
 */
public class test {
    public class CheckOutServlet extends HttpServlet {

    ProductDAO pdao = new ProductDAO();
    OrderDAO orderdao = new OrderDAO();
    CartDAO cartdao = new CartDAO();

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

        if (selectedProducts_id == null || selectedProducts_id.length == 0) {
            List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
            request.setAttribute("listCarts", listCarts);
            request.setAttribute("message", "Bạn chưa chọn sản phẩm nào để thanh toán.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        }

        List<CartDTO> selectedProducts = new ArrayList<>();
        boolean outStock = false;
        StringBuilder stockMessage = new StringBuilder();

//        for (String selected_product_id : selectedProducts_id) {
//            int product_id = Integer.parseInt(selected_product_id);
//            CartDTO cartProduct = cartdao.getCart(user.getUser_id(), product_id);
//            ProductDTO product = pdao.getProductById(product_id);
//            System.out.println(cartProduct);
//            System.out.println(product);
//            if (cartProduct == null || product == null) {
//                outStock = true;
//                stockMessage.append("Sản phẩm không tồn tại.<br>");
//                request.setAttribute("message", stockMessage.toString());
//                List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
//                request.setAttribute("listCarts", listCarts);
//                request.getRequestDispatcher("cart.jsp").forward(request, response);
//                return;
//            } else if (cartProduct.getQuantity() > product.getStock()) {
//                outStock = true;
//                System.out.println("ko đủ số lượng để bán!");
//                stockMessage.append("Sản phẩm ").append(product.getTitle())
//                        .append(" chỉ còn ").append(product.getStock()).append(" sản phẩm!<br>");
//                request.setAttribute("message", stockMessage.toString());
//                List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
//                request.setAttribute("listCarts", listCarts);
//                request.getRequestDispatcher("cart.jsp").forward(request, response);
//                return;
//            } else {
//                System.out.println("add thành công");
//                selectedProducts.add(cartProduct);
//            }
//        }
        for (int i = 0; i < selectedProducts_id.length; i++) {
            int product_id = Integer.parseInt(selectedProducts_id[i]);
            int newQuantity = Integer.parseInt(quantities[i]);  

            CartDTO cartProduct = cartdao.getCart(user.getUser_id(), product_id);
            ProductDTO product = pdao.getProductById(product_id);

            if (cartProduct == null || product == null) {
                outStock = true;
                stockMessage.append("Sản phẩm không tồn tại.<br>");
            } else {
                int oldQuantity = cartProduct.getQuantity();
                int quantityDifference = newQuantity - oldQuantity;  

                if (quantityDifference > product.getStock()) {
                    outStock = true;
                    stockMessage.append("Sản phẩm ").append(product.getTitle())
                            .append(" chỉ còn ").append(product.getStock()).append(" sản phẩm!<br>");
                } else {
                    // Cập nhật số lượng trong giỏ hàng
                    cartdao.updateCartQuantity(user.getUser_id(), product_id, newQuantity);
                    cartProduct.setQuantity(newQuantity);

                    // Trừ số lượng thực tế trong kho
                    pdao.updateStock(product_id, quantityDifference);

                    selectedProducts.add(cartProduct);
                }
            }
        }

        for (CartDTO cart : selectedProducts) {
            System.out.println(cart);

        }

        if (outStock) {
            List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
            request.setAttribute("listCarts", listCarts);
            request.setAttribute("message", stockMessage.toString());
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        }

        double totalPrice = 0;
        for (CartDTO cart : selectedProducts) {
            totalPrice += cart.getQuantity() * cart.getProduct().getPrice();
        }

        OrderDTO newOrder = new OrderDTO(0, user.getUser_id(), totalPrice, "Pending");
        boolean isOrderCreated = orderdao.createOrder(newOrder);

        if (isOrderCreated) {
            for (CartDTO item : selectedProducts) {
                pdao.updateStock(item.getProduct().getProduct_id(), item.getQuantity());
            }
            for (CartDTO item : selectedProducts) {
                cartDAO.removeFromCart(user.getUser_id(), item.getProduct().getProduct_id());
            }
            List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
            request.setAttribute("listCarts", listCarts);
            request.setAttribute("message", "Thanh toán thành công!");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            List<CartDTO> listCarts = cartDAO.getCartByUserID(user.getUser_id());
            request.setAttribute("listCarts", listCarts);
            request.setAttribute("message", "Thanh toán thất bại!");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }

    }
}
}
