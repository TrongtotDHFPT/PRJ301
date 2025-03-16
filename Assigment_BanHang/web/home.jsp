<%-- 
    Document   : home
    Created on : Mar 15, 2025, 9:32:34 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:if test="${not empty sessionScope.user}">
            <form action="search" method="get">
                <input type="text" name="searchTerm" placeholder="Search products..." required />
                <button type="submit">🔍</button>
            </form>

            <!-- Hiển thị sản phẩm -->
            <c:forEach var="product" items="${list}">
                <div>
                    <h3>${product.product_name}</h3>
                    <p>${product.description}</p>
                    <p>Price: ${product.price}</p>
                    <img src="${product.image}" width="100px" height="100px">
                    <p>Stock: ${product.stock_quantity}</p>
                </div>
            </c:forEach>
            
        </c:if>


        <jsp:include page="footer.jsp"/>
    </body>
</html>
