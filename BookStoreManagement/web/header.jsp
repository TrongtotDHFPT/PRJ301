<%-- 
    Document   : header
    Created on : Mar 16, 2025, 3:05:23 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Clothing Shop</title>
        <style>
            body { font-family: Arial; margin: 0; padding: 0; }
            .header { background: #333; color: white; padding: 10px 20px; display: flex; justify-content: space-between; align-items: center; }
            .header a { color: white; margin: 0 10px; text-decoration: none; }
            .header a:hover { text-decoration: underline; }
            .logo { font-size: 24px; font-weight: bold; }
            .menu { display: flex; align-items: center; }
            .search-bar input[type="text"] {
                padding: 5px; 
                width: 800px;}
            .search-bar button { padding: 5px; cursor: pointer; }
        </style>
    </head>
    <body>

        <div class="header">
            <div class="logo">
                <a href="home" style="color: white; text-decoration: none;">🛍️ Clothing Shop</a>
            </div>

            <div class="search-bar" style="margin-left: 20px;">
<!--                <form action="search" method="get">
                    <input type="text" name="keyword" placeholder="Search products..." required />
                    <button type="submit">🔍</button>
                </form>-->
            </div>

            <div class="menu">
                <a href="home">Home</a>
                <a href="viewCart">Cart</a>
                <a href="profile.jsp">Account</a>

                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <span>Hello, ${sessionScope.user.name} | <a href="logout">Logout</a></span>
                    </c:when>
                    <c:otherwise>
                        <a href="login.jsp">Login</a> | <a href="register.jsp">Register</a>
                    </c:otherwise>
                </c:choose>

                <!-- Search Bar -->

            </div>
        </div>

        <div style="padding: 20px;"> <!-- Mở body content -->
