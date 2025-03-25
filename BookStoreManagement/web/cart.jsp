<%-- 
    Document   : cart
    Created on : Mar 19, 2025, 7:04:01 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.List" %>
<%@ page import="dto.CartDTO" %>
<!DOCTYPE html

    <html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
    <%@include file="header.jsp" %>
    <h2>Your Cart</h2>

    <c:if test="${empty listCarts}">
        Giỏ hàng(0 sản phẩm)
        <p>Chưa có sản phẩm trong giỏ hàng của bạn.</p>
        <img src="assets/img/cart_null.jpg">
    </c:if>
        
    <c:if test="${not empty listCarts}">

        <table border="1">
            <tr>
                <th>No</th>
                <th>Image</th>
                <th>Title</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
            <%int count = 0;%>
            <c:forEach var="item" items="${listCarts}">
                <%count++;%>
                <tr>
                    <td><%=count%></td>
                    <td><img src="assets/img/${item.product.image}" width="50"></td>
                    <td>${item.product.title}</td>
                    <td>${item.product.price}</td>
                    <td>${item.quantity}</td>
                    <td><fmt:formatNumber value="${item.product.price * item.quantity}" type="number" groupingUsed="true"/> ₫</td>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="product_id" value="${item.product.product_id}"/>
                            <input type="hidden" name="user_id" value="${user.user_id}"/>
                            <button type="submit">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="home">Mua sắm ngay</a>
    <%@include file="footer.jsp" %>
</body>
</html>

