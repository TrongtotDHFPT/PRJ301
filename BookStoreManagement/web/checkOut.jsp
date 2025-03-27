<%-- 
    Document   : checkOut
    Created on : Mar 26, 2025, 7:55:55 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css">

    </head>

    <body>
        <%@include file="header.jsp" %>
        <h2>Thông tin đơn hàng</h2>
        <p>Tên khách hàng: ${sessionScope.user.name}</p>
        <p>Email: ${sessionScope.user.email}</p>
        <p>Số điện thoại: ${sessionScope.user.phone}</p>
        <p>Địa chỉ giao hàng: ${sessionScope.user.address}</p>

        <h3>Danh sách sản phẩm</h3>
        <table border="1">
            <tr>
                <th>No</th>
                <th>Hình ảnh</th>
                <th>Tên sách</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Thành tiền</th>
            </tr>

            <% int count = 0; %>
            <c:forEach var="item" items="${selectedProducts}">
                <% count++;%>
                <tr>
                    <td><%= count%></td>
                    <td><img src="assets/img/${item.product.image}" width="50"></td>
                    <td>${item.product.title}</td>
                    <td><fmt:formatNumber value="${item.product.price}" type="number" groupingUsed="true"/> ₫</td>
                    <td>${item.quantity}</td>
                    <td><fmt:formatNumber value="${item.product.price * item.quantity}" type="number" groupingUsed="true"/> ₫</td>
                </tr>
            </c:forEach>
        </table>

        <h3>Tổng số tiền: <fmt:formatNumber value="${totalPrice}" type="number" groupingUsed="true"/> ₫</h3>

        <form action="confirmCheckout" method="get">
            <button type="submit" name="action" value="confirm">Xác nhận</button>
            <button type="submit" name="action" value="cancel">Hủy bỏ</button>
        </form>

        <a href="cart.jsp">Quay lại giỏ hàng</a>

        <%@include file="footer.jsp" %>
    </body>
</html>
