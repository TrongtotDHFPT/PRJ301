<%-- 
    Document   : header
    Created on : Mar 16, 2025, 3:05:23 PM
    Author     : trong
--%>

<%@page import="utils.AuthUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My Book Store</title>
        <style>
 
            * { margin: 0; padding: 0; box-sizing: border-box; font-family: Arial, sans-serif; }
            html, body { height: 100%; display: flex; flex-direction: column; }

    
            .wrapper { display: flex; flex-direction: column; min-height: 100vh; }
            .main-content { flex: 1; padding: 20px; }

            /* Header */
            .header {
                position: fixed;  
                width: 100%;   
                background: #333; 
                color: white; 
                padding: 10px 20px; 
                display: flex; 
                justify-content: space-between; 
                align-items: center;
            }
            .header a { color: white; margin: 0 10px; text-decoration: none; }
            .header a:hover { text-decoration: underline; }
            .logo { font-size: 24px; font-weight: bold; }

            /* Menu */
            .menu { display: flex; align-items: center; }
            .search-bar input[type="text"] { padding: 5px; width: 800px; }
            .search-bar button { padding: 5px; cursor: pointer; }

             .footer {
                background: #333; 
                color: white; 
                text-align: center; 
                padding: 20px;
                margin-top: auto;  
            }
            .footer a { color: #FFD700; text-decoration: none; }
            .footer a:hover { text-decoration: underline; }
        </style>
    </head>
    <body>

        <div class="wrapper">
            <div class="header">
                <div class="logo">
                    <a href="home" style="color: white; text-decoration: none;">🛍️ Book Store</a>
                </div>

                <div class="search-bar" style="margin-left: 20px;">
                </div>

                <div class="menu">
                    <a href="home">Home</a>
                    <%
                        if (AuthUtils.isAdmin(session)) {
                    %>    
                    <a href="managerProducts?action=">Manager Product</a>
                    <a href="addCategory">Manager Category</a>
                    <%}%>
                    <c:if test="${not empty sessionScope.user}">
                        <a href="viewCart">Cart</a>
                        <a href="account.jsp">Account</a>
                    </c:if>




                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <span>Hello, ${sessionScope.user.name} | <a href="logout">Logout</a></span>
                        </c:when>
                        <c:otherwise>
                            <a href="login.jsp">Login</a> | <a href="register.jsp">Register</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="main-content">
