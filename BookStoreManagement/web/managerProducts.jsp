<%-- 
    Document   : managerProducts
    Created on : Mar 22, 2025, 12:38:36 AM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="utils.AuthUtils"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ManagerProduct</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%
            if (AuthUtils.isAdmin(session)) {
        %>
        <h1>Manager Product</h1>
        <form action="managerProducts" method="get">
            <input type="hidden" name="action" value="search" />
            <input type="text" name="searchTerm" placeholder="Search products..." value="${(not empty searchTerm) ?searchTerm: ""}"/>
            <input type="submit" value="Search"/>
        </form>
        <a href="addProduct.jsp">+Add product</a><br>

        <c:if test="${not empty message}">
            <span style="color: red;">${message}</span>
        </c:if>

        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Image</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Category ID</th>
                        <th>Action</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${list}">
                        <tr>
                            <td>${product.product_id}</td>
                            <td><img src="assets/img/${product.image}" style="width: 100px;"></td>
                            <td>${product.title}</td>
                            <td>${product.author}</td>
                            <td>${product.description}</td>
                            <td>${product.price}</td>
                            <td>${product.stock}</td>
                            <td>${product.category_id}</td>
                            <td>
                                <a href="managerProducts?action=delete&product_id=${product.product_id}"><img src="icon/trash_icon.png" style="width: 25px"> </a>
                            </td>
                            <td>
                                <a href="addProduct.jsp?product_id=${product.product_id}"><img src="icon/update_icon.png" style="width: 25px"> </a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Pagination -->
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href ="managerProducts?page=${currentPage-1}">&laquo;Previous</a>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="page">
                    <c:choose>
                        <c:when test="${page == currentPage}">
                            <span class="current-page">${page}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="managerProducts?page=${page}">${page}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="managerProducts?page=${currentPage+1}"> Next &raquo;</a>
                </c:if>
            </div>
        </c:if>
        <%}%>
        <%@include file="footer.jsp" %>
    </body>
</html>