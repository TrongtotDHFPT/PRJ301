/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.ProductDAO;
import dto.ProductDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trong
 */
public class ViewDetailServlet extends HttpServlet {

    public static final String VIEW_PAGE = "viewDetail.jsp";
    public static final ProductDAO pdao = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = VIEW_PAGE;
        String strProduct_id = request.getParameter("product_id");

        try {
            int product_id = Integer.parseInt(strProduct_id);
            ProductDTO product = pdao.readByID(product_id);
            List<ProductDTO> list_sameCategory = pdao.searchByCategoryID(product_id);
            request.setAttribute("product", product);
            request.setAttribute("list_sameCategory", list_sameCategory);
            url = VIEW_PAGE;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

}
