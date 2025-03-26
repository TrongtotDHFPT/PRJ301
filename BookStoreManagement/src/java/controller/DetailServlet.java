package controller;

import dao.ProductDAO;
import dto.ProductDTO;
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
public class DetailServlet extends HttpServlet {

    public static final String VIEW_PAGE = "viewDetail.jsp";
    public static final ProductDAO pdao = new ProductDAO();
    public static final int itemsPerPage = 5;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = VIEW_PAGE;
        String strProduct_id = request.getParameter("product_id");
        HttpSession session = request.getSession();
      
        try {
            UserDTO user = (UserDTO) session.getAttribute("user");
            int product_id = Integer.parseInt(strProduct_id);
            ProductDTO product = pdao.getProductById(product_id);
            List<ProductDTO> fullList = pdao.getProductByCategoryID(product.getCategory_id());

            int page = 1;
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                try {
                    page = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                    page = 1;
                }
            }

            int start = (page - 1) * itemsPerPage;
            int end = Math.min(start + itemsPerPage, fullList.size());

            List<ProductDTO> paginatedList = fullList.subList(start, end);

            // Tính tổng số trang
            int totalPages = (int) Math.ceil((double) fullList.size() / itemsPerPage);

            request.setAttribute("user", user);
            request.setAttribute("product", product);
            request.setAttribute("list_sameCategory", paginatedList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("product_id", product_id);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);//To change body of generated methods, choose Tools | Templates.
    }

}
