/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
import dto.CategoryDTO;
import dto.ProductDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author trong
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // >= 2MB lưu tạm vào RAM
        maxFileSize = 1024 * 1024 * 10, // 10MB         // tối đa 10MB Server từ chối
        maxRequestSize = 1024 * 1024 * 50)    //   tổng dung lượng các file trong request không vượt quá50MB
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //if isadmin    
        String url = "managerProducts.jsp";
        try {
            boolean checkError = false;
            String productIdParam = request.getParameter("product_id");
            int product_id = 0;

            if (productIdParam != null && !productIdParam.trim().isEmpty()) {
                try {
                    product_id = Integer.parseInt(productIdParam);
                } catch (NumberFormatException e) {
                    checkError = true;
                }
            }

            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String description = request.getParameter("description");

            double price = 0;
            int stock = 0;
            int category_id = 0;
            String imagePath = "default.jpg";

            if (title == null || title.trim().isEmpty()) {
                checkError = true;
                request.setAttribute("txtTitle_error", "Title cannot be empty.");
            }

            if (author == null || author.trim().isEmpty()) {
                author = "";
            }

            if (description == null || description.trim().isEmpty()) {
                description = "";
            }

            try {
                price = Double.parseDouble(request.getParameter("price"));
                if (price < 0) {
                    checkError = true;
                    request.setAttribute("txtPrice_error", "Price must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                checkError = true;
                request.setAttribute("txtPrice_error", "Price must be a valid number.");
            }

            try {
                stock = Integer.parseInt(request.getParameter("stock"));
                if (stock < 0) {
                    checkError = true;
                    request.setAttribute("txtStock_error", "Stock must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                checkError = true;
                request.setAttribute("txtStock_error", "Stock must be a valid number.");
            }

            try {
                category_id = Integer.parseInt(request.getParameter("category_id"));
            } catch (NumberFormatException e) {
                checkError = true;
                request.setAttribute("txtCategoryID_error", "Category ID must be a valid number.");
            }

            //làm rõ hơn
            Part filePart = request.getPart("image");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();// Lấy tên gốc của file mà người dùng đã tải lên.
                String savePath = getServletContext().getRealPath("/") + "assets/img/" + fileName;//Xác định nơi sẽ lưu file trên server.
                filePart.write(savePath);
                imagePath = fileName;
            } else {
                imagePath = "default.jpg";
            }

            ProductDTO product = new ProductDTO(product_id, title, author, price, stock, imagePath, category_id, description);
            ProductDAO pdao = new ProductDAO();
            if (!checkError) {
                if (product_id > 0) {
                    if (pdao.update(product)) {
                        request.setAttribute("message", "Update Book successfully.");
                    } else {
                        request.setAttribute("message", "Update fail");
                    }

                } else {
                    if (pdao.create(product)) {
                        request.setAttribute("message", "Add Book Successfully");
                    } else {
                        request.setAttribute("message", "Add fail");
                    }
                }
                url = "addProduct.jsp";
            } else {
                request.setAttribute("product", product);
                request.setAttribute("message", "Action Fail");
                url = "addProduct.jsp";
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
