package controller;

import dao.ProductDAO;
import dto.ProductDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {

    private static final int PRODUCTS_PER_PAGE = 6;
    ProductDAO pdao = new ProductDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tham số tìm kiếm (nếu có)
            String searchTerm = request.getParameter("searchTerm");
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                searchTerm = "";
            }

            String pageParameter = request.getParameter("page");
            int currentPage = 1;
            if (pageParameter != null && !pageParameter.isEmpty()) { // còn nếu k thì lấy current
                currentPage = Integer.parseInt(pageParameter);
            }

            List<ProductDTO> allProducts;
            if (searchTerm.trim().isEmpty()) {
                allProducts = pdao.readAll();
            } else {
                allProducts = pdao.search(searchTerm);
            }

            //Phân tranggggggg
            int totalProducts = allProducts.size();
            int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);// tổng trang = tổng sp / số sp trên 1 trang

            //cắt list
            int startIndex = (currentPage - 1) * PRODUCTS_PER_PAGE;
            int endIndex = Math.min(startIndex + PRODUCTS_PER_PAGE, totalProducts);
            List<ProductDTO> productsForPage = allProducts.subList(startIndex, endIndex);
            
            if(productsForPage == null){
                request.setAttribute("message_ForSearch", "88888888888888888888");
                //cái này sai nhưng để đây để nhắc nhở nếu search k có gì thì sẽ làm gì
            }
            request.setAttribute("productsForPage", productsForPage);
            
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("searchTerm", searchTerm);
            
            request.setAttribute("totalPages", totalPages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

}
