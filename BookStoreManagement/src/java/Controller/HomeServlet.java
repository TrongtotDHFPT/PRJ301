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
        try {

            CategoryDAO cdao = new CategoryDAO();
            List<CategoryDTO> cateList = cdao.readAll();
            request.setAttribute("cateList", cateList);

            String searchTerm = request.getParameter("searchTerm");
            String orderBy = request.getParameter("orderBy");
            String categoryPara = request.getParameter("category_id");
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                searchTerm = "";
            }
            if (orderBy == null || orderBy.trim().isEmpty()) {
                orderBy = "";
            }

            int category_id = -1;
            if (categoryPara != null && !categoryPara.trim().isEmpty()) {
                try {
                    category_id = Integer.parseInt(categoryPara);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException category_id: " + e.getMessage());
                }
            }

            List<ProductDTO> allProducts = null;
            if (!searchTerm.isEmpty() && category_id != -1) {
                allProducts = pdao.searchBySearchAndCategoryID(searchTerm, category_id);
            } else if (searchTerm.isEmpty() && category_id == -1) {
                allProducts = pdao.readAll();
            } else if (!searchTerm.isEmpty() && category_id == -1) {
                allProducts = pdao.search(searchTerm);
            } else if (searchTerm.isEmpty() && category_id != -1) {
                allProducts = pdao.getProductByCategoryID(category_id);
            }
            allProducts = pdao.getProductsAndOrderBy(searchTerm, category_id, orderBy);

            //lastProduct
            ProductDTO lastProduct = pdao.getLatestProduct(category_id);
            request.setAttribute("lastProduct", lastProduct);
            //Phân trang =======================================================
            String pageParameter = request.getParameter("page");
            int currentPage = 1;
            if (pageParameter != null && !pageParameter.isEmpty()) {
                currentPage = Integer.parseInt(pageParameter);
            }
            int totalProducts = allProducts.size();
            int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

            //cắt list
            int startIndex = (currentPage - 1) * PRODUCTS_PER_PAGE;
            int endIndex = Math.min(startIndex + PRODUCTS_PER_PAGE, totalProducts);
            List<ProductDTO> productsForPage = allProducts.subList(startIndex, endIndex);

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
