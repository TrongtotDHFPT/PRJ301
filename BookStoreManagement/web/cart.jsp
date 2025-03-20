<%-- 
    Document   : cart
    Created on : Mar 19, 2025, 7:04:01 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <p>${message}</p>
        --chỗ này để cái ảnh vào--  
    </c:if>
    <c:if test="${not empty listCarts}">

        <table border="1">
            <tr>
                <th>Image</th>
                <th>Title</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
            <c:forEach var="item" items="${listCarts}">
                <tr>
                    <td><img src="assets/img/${item.product.image}" width="50"></td>
                    <td>${item.product.title}</td>
                    <td>${item.product.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.product.price * item.quantity}</td>
                    <td>
                        <form action="cart" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="product_id" value="${item.product.product_id}"/>
                            <input type="hidden" name="user_id" value="${user.user_id}"/>
                            <button type="submit">🗑 Delete</button>
                        </form>
                    </td>

                </tr>
                <c:if test="${not empty error_Delete}">
                    ${error_Delete}
                </c:if>
            </c:forEach>
        </table>
    </c:if>

    <a href="home">Mua sắm ngay</a>
    <%@include file="footer.jsp" %>
</body>
</html>

