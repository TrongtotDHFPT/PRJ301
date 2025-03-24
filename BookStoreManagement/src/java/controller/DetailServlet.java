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
public class DetailServlet extends HttpServlet{
    public static final String VIEW_PAGE = "viewDetail.jsp";
    public static final ProductDAO pdao = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = VIEW_PAGE;
        String strProduct_id = request.getParameter("product_id");
        HttpSession session = request.getSession();
        try {
            UserDTO user = (UserDTO)session.getAttribute("user");
            int product_id = Integer.parseInt(strProduct_id);
            ProductDTO product = pdao.getProductById(product_id);
            List<ProductDTO> list_sameCategory = pdao.getProductByCategoryID(product.getCategory_id());
            request.setAttribute("user", user);
            request.setAttribute("product", product);
            request.setAttribute("list_sameCategory", list_sameCategory);
            url = VIEW_PAGE;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);//To change body of generated methods, choose Tools | Templates.
    }
    
}
