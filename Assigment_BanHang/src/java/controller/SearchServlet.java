/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class SearchServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String searchTerm = request.getParameter("searchTerm");
         String url = "home.jsp";
         try {
            ProductDAO pdao =new ProductDAO();
            List<ProductDTO> list = pdao.search(searchTerm);
            request.setAttribute("list", list);
            url = "home.jsp";
        } catch (Exception e) {
        }finally{
             request.getRequestDispatcher(url).forward(request, response);
         }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
