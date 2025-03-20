package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import dto.CategoryDTO;
import dto.ProductDTO;
import dto.UserDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {

    private static final int PRODUCTS_PER_PAGE = 6;
    ProductDAO pdao = new ProductDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            System.out.println("Session user is null, redirecting to login...");
            response.sendRedirect("login.jsp");
            return;
        } else {
            System.out.println("User found in session: " + user.getUsername());
        }

        try {
            //lastProduct
            ProductDTO lastProduct = pdao.getLatestProduct();
            request.setAttribute("lastProduct", lastProduct);
            
            //list category name chưa xong
//            CategoryDAO cdao = new CategoryDAO();
//            List<CategoryDTO> cateList = cdao.readAll();
//            request.setAttribute("cateList", cateList);


            String searchTerm = request.getParameter("searchTerm");
            String orderBy = request.getParameter("orderBy");
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                searchTerm = "";
            }
            if (orderBy == null || orderBy.trim().isEmpty()) {
                orderBy = "";
            }
            
            
            String pageParameter = request.getParameter("page");
            int currentPage = 1;
            if (pageParameter != null && !pageParameter.isEmpty()) { // còn nếu k thì lấy current
                currentPage = Integer.parseInt(pageParameter);
            }

            List<ProductDTO> allProducts;
            if (searchTerm.trim().isEmpty()) {
                allProducts = pdao.readAll();
                allProducts = pdao.readAllOrder(orderBy);
            } else {
                allProducts = pdao.search(searchTerm);
                allProducts = pdao.readAllOrder(orderBy);
            }

            //Phân trang 
            int totalProducts = allProducts.size();
            int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

            //cắt list
            int startIndex = (currentPage - 1) * PRODUCTS_PER_PAGE;
            int endIndex = Math.min(startIndex + PRODUCTS_PER_PAGE, totalProducts);
            List<ProductDTO> productsForPage = allProducts.subList(startIndex, endIndex);

            if (productsForPage == null) {
                request.setAttribute("message_ForSearch", "88888888888888888888");
                //cái này sai nhưng để đây để nhắc nhở nếu search k có gì thì sẽ làm gì
            }
            request.setAttribute("productsForPage", productsForPage);

            request.setAttribute("currentPage", currentPage);
            request.setAttribute("searchTerm", searchTerm);
            request.setAttribute("orderBy", orderBy);
            request.setAttribute("totalPages", totalPages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

}
