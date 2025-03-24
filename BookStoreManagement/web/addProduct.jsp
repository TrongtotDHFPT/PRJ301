<%-- 
    Document   : addProduct
    Created on : Mar 22, 2025, 1:27:06 AM
    Author     : trong
--%>

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
    %>
    <body>
        <%@include file="header.jsp" %>
        <h1>Book Form</h1>
        <form action="addProduct" method="post" enctype="multipart/form-data">
            <!-- Title -->
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" value="${product.title}" required>
            <c:if test="${not empty txtTitle_error}">
                <span style="color: red;">${txtTitle_error}</span>
            </c:if>
            <br>

            <!-- Author -->
            <label for="author">Author:</label>
            <input type="text" id="author" name="author" value="${product.author}">
            <br>

            <!-- Price -->
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" value="${product.price}" required>
            <c:if test="${not empty txtPrice_error}">
                <span style="color: red;">${txtPrice_error}</span>
            </c:if>
            <br>

            <!-- Stock -->
            <label for="stock">Stock:</label>
            <input type="number" id="stock" name="stock" value="${product.stock}" required>
            <c:if test="${not empty txtStock_error}">
                <span style="color: red;">${txtStock_error}</span>
            </c:if>
            <br>

            <!-- Category -->
            <label for="category">Category:</label>
            <select id="category" name="category_id" required>
                <c:forEach var="cate" items="${cateList}">
                    <option value="${cate.category_id}" ${cate.category_id == product.category_id ? 'selected' : ''}>
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
            <input type="file" id="image" name="image" accept="image/*" >
            <br>

            <!-- Description -->
            <label for="description">Description:</label><br>
            <textarea id="description" name="description" rows="4" cols="50">${product.description}</textarea>
            <br>

            <button type="submit">Add</button>
            <c:if test="${not empty message}">
                <span style="color: green;">${message}</span>
            </c:if>            
        </form>
        <a href=""></a>

        <%@include file="footer.jsp" %>
    </body>
</html>
