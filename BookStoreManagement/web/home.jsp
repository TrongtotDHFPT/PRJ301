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
        <%@include file="header.jsp" %>
        <h1>Welcome to Home Page</h1>

        <!-- Search Form -->
        <form action="home" method="get">
            <input type="text" name="searchTerm" placeholder="Search products..." value="${searchTerm}">
            <button type="submit">Search</button>
        </form>

        <div class="product-list">
            <c:forEach var="product" items="${productsForPage}">
                <div class="image_product">
                    <img src= "assets/img/${product.image}"style="width:150px;height:150px;"/> 
                </div>    

                <div class="name_product">
                    <h3>${product.title}</h3>
                    By ${product.author}
                </div>
                <div>
                    <h5>$${product.price} </h5>
                    Product ID :    ${product.product_id} 

                </div>
                <div>
                    <c:if test="${product.stock > 0}">
                        <p> In stock  </p>
                    </c:if>
                    <c:if test="${product.stock <= 0}">
                        <p> Out stock </p>
                    </c:if>
                </div>
                    
                    <a href="viewDetail?product_id=${product.product_id}">View Detail</a>
                    <p>---------------------------------------------------------------------------------------------</p>    
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
        <%@include file="footer.jsp" %>
    </body>
</html>