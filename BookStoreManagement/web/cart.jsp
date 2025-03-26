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

    <c:if test="${empty listCarts}">
        Giỏ hàng(0 sản phẩm)
        <p>Chưa có sản phẩm trong giỏ hàng của bạn.</p>
        <img src="assets/img/emptycart.jpg" >
    </c:if>

    <c:if test="${not empty listCarts}">
        <h3>Giỏ hàng(${listCarts.size()} sản phẩm)</h3>
        <form action="checkOut" method="post">
            <table border="1">
                <tr>
                    <th>Select</th>
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
                        <td>
                            <input type="checkbox" name="selectedProducts" value="${item.product.product_id}" />
                        </td>
                        <td><%=count%></td>
                        <td><img src="assets/img/${item.product.image}" width="50"></td>
                        <td>${item.product.title}</td>
                        <td><fmt:formatNumber value="${item.product.price}" type="number" groupingUsed="true"/> ₫</td>
                        <td><input type="number" name="quantity" value="${item.quantity}" min ="1"  required=""/></td> 
                        <td><fmt:formatNumber value="${item.product.price * item.quantity}" type="number" groupingUsed="true"/> ₫</td>
                        <td>
                            <a href="cart?action=delete&product_id=${item.product.product_id}">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit">Mua hàng</button>
        </form>
        <c:if test="${not empty message}">${message} <br></c:if>
        <c:if test="${not empty totalPrice}">
                    <h3>Tổng số tiền ${totalPrice}</h3>
                </c:if>
    </c:if>

    <a href="home">Mua sắm ngay</a>
    <%@include file="footer.jsp" %>
</body>
</html>

