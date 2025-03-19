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
            <h2>Your Cart</h2>

            <c:if test="${empty sessionScope.carts}">
                <p>Cart is empty!</p>
            </c:if>
            <c:if test="${not empty sessionScope.carts}">
                <c:forEach var="cart" items="${sessionScope.carts}">
                    <p>Product ID: ${cart.product_id}, Quantity: ${cart.quantity}</p>
                </c:forEach>
            </c:if>
            <a href="home">Continue Shopping</a>
        </body>
    </html>

