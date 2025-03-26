<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Book Store</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <body>
        <header class="header">
            <div class="header-container">
                <div class="logo">
                    <a href="home"><i class="fas fa-book-open logo-icon"></i> Book Store</a>
                </div>


                <nav class="menu">
                    <a href="home"><i class="fas fa-home"></i> Home</a>

                    <c:if test="${sessionScope.user.role == 'ADMIN'}">
                        <a href="managerProducts?action="><i class="fas fa-boxes"></i> Manage Products</a>
                        <a href="addCategory"><i class="fas fa-tags"></i> Manage Categories</a>
                    </c:if>

                    <c:if test="${not empty sessionScope.user}">
                        <a href="viewCart" class="cart-icon">
                            <i class="fas fa-shopping-cart"></i>
                            <c:if test="${sessionScope.cartCount > 0}">
                                <span class="cart-count">${sessionScope.cartCount}</span>
                            </c:if>Cart
                        </a>
                        <a href="account.jsp"><i class="fas fa-user"></i> Account</a>
                    </c:if>

                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <span class="user-greeting">Hello, ${sessionScope.user.name} | <a href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a></span>
                        </c:when>
                        <c:otherwise>
                            <a href="login.jsp"><i class="fas fa-sign-in-alt"></i> Login</a>
                            <a href="register.jsp"><i class="fas fa-user-plus"></i> Register</a>
                        </c:otherwise>
                    </c:choose>
                </nav>
            </div>
        </header>

        <div class="main-content">