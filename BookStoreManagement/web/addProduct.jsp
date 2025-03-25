<%-- 
    Document   : addProduct
    Created on : Mar 22, 2025, 1:27:06 AM
    Author     : trong
--%>

<%@page import="dao.ProductDAO"%>
<%@page import="dto.ProductDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dto.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="dao.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <%
        CategoryDAO cdao = new CategoryDAO();
        List<CategoryDTO> cateList = cdao.readAll();
        request.setAttribute("cateList", cateList);
        //to update

        String productIdParam = request.getParameter("product_id");
        ProductDTO product = null;

        if (productIdParam != null && !productIdParam.isEmpty()) {
            try {
                int product_id = Integer.parseInt(productIdParam);
                ProductDAO pdao = new ProductDAO();
                product = pdao.getProductById(product_id);
                request.setAttribute("product", product);
            } catch (NumberFormatException e) {
                System.out.println("Invalid product_id: " + productIdParam);
            }
        }
    %>
    <body>
        <%@include file="header.jsp" %>
        <%
            if (AuthUtils.isAdmin(session)) {
        %>
        <h1>Book Form ${product != null ? "UPDATE BOOK" : "ADD BOOK"}</h1>

        <form action="addProduct" method="post" enctype="multipart/form-data">
            <!-- Nếu cập nhật thì gửi thêm product_id -->
            <c:if test="${product != null}">
                <input type="hidden" name="product_id" value="${product.product_id}">
            </c:if>

            <!-- Title -->
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" value="${product != null ? product.title : ''}" >
            <c:if test="${not empty txtTitle_error}">
                <span style="color: red;">${txtTitle_error}</span>
            </c:if>
            <br>

            <!-- Author -->
            <label for="author">Author:</label>
            <input type="text" id="author" name="author" value="${product != null ? product.author : ''}">
            <br>

            <!-- Price -->
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" value="${product != null ? product.price : ''}" >
            <c:if test="${not empty txtPrice_error}">
                <span style="color: red;">${txtPrice_error}</span>
            </c:if>
            <br>

            <!-- Stock -->
            <label for="stock">Stock:</label>
            <input type="number" id="stock" name="stock" value="${product != null ? product.stock : ''}" >
            <c:if test="${not empty txtStock_error}">
                <span style="color: red;">${txtStock_error}</span>
            </c:if>
            <br>

            <!-- Category -->
            <label for="category">Category:</label>
            <select id="category" name="category_id" required>
                <c:forEach var="cate" items="${cateList}">
                    <option value="${cate.category_id}" ${product != null && cate.category_id == product.category_id ? 'selected' : ''}>
                        ${cate.category_name}
                    </option>
                </c:forEach>
            </select>
            <c:if test="${not empty txtCategoryID_error}">
                <span style="color: red;">${txtCategoryID_error}</span>
            </c:if>
            <br>

            <!-- Image -->
            <label for="image">Image:</label>
            <input type="file" id="image" name="image" accept="image/*">
            <c:if test="${product != null && not empty product.image}">
                <br>
                <img src="assets/img/${product.image}" style="width: 100px;">
                <input type="hidden" name="old_image" value="${product.image}">
            </c:if>
            <br>

            <!-- Description -->
            <label for="description">Description:</label><br>
            <textarea id="description" name="description" rows="4" cols="50">${product != null ? product.description : ''}</textarea>
            <br>

            <!-- Submit Button -->
            <button type="submit">${product != null ? "Update" : "Add"}</button>
            <c:if test="${not empty message}">
                <span style="color: green;">${message}</span>
            </c:if> 
        </form>
        <a href="managerProducts?action=search&searchTerm=">Back to Product List</a>

        <%}%>
        
        <%
            if (!AuthUtils.isAdmin(session)) {
        %>
        <p> Dont permission to access this page!</p>
        <%}%>
        <%@include file="footer.jsp" %>
    </body>
</html>