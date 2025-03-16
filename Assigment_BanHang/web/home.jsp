<%-- 
    Document   : home
    Created on : Mar 15, 2025, 9:32:34 PM
    Author     : trong
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
    </head>
    <body>

        <h1>Welcome to Home Page</h1>

        <!-- Search Form -->
        <form action="home" method="get">
            <input type="text" name="searchTerm" placeholder="Search products..." value="${searchTerm}">
            <button type="submit">Search</button>
        </form>

        <div class="product-list">
            <c:forEach var="product" items="${productsForPage}">
                <div class="image_product">
                    <img src="${product.image} " style="width:150px;height:150px;"/> 
                </div>    

                <div class="name_product">
                    ${product.product_name}
                </div>
                <div>
                    ${product.description}
                    ${product.price}
                </div>
                <div>
                    <c:if test="${product.stock_quantity > 0}">
                        <p> Còn hàng </p>
                    </c:if>
                    <c:if test="${product.stock_quantity <= 0}">
                        <p> Sản phẩm đã hết hàng </p>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <!--Phân trang-->
        <div class="pagination">
            <!-- Previous -->
            <c:if test="${currentPage > 1}">
                <a href ="home?page=${currentPage-1}&searchTerm=${searchTerm}">&laquo;Previous</a>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" var="page">
                <c:choose>
                    <c:when test="${page == currentPage}">
                        <span class="current-page">${page}</span> <!-- Trang hiện tại, ko click -->
                    </c:when>
                    <c:otherwise>
                        <a href="home?page=${page}&searchTerm=${searchTerm}">${page}</a> <!-- Trang khác -->
                    </c:otherwise>
                </c:choose>
            </c:forEach>
                        
            <!-- Next -->        
            <c:if test="${currentPage < totalPages}">
                <a href="home?page=${page+1}&searchTerm=${searchTerm}"> Next &laquo;</a>
            </c:if>
        </div>
    </body>
</html>