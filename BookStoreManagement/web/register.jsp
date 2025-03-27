<%-- 
    Document   : register
    Created on : Mar 16, 2025, 1:49:47 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>        
        <!--<link rel="stylesheet" type="text/css" href="css/register.css">-->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/register.css">

    </head>
    <body>
        <h1>Register Form</h1>
        <form action="register" method="post">
            <label>Name:</label>
            <input type="text" name="name" value="${param.name}"/>
            <c:if test="${not empty Error_strName}">
                <span class="error">${Error_strName}</span>
            </c:if>

            <label>Username:</label>
            <input type="text" name="username" value="${param.username}"/>
            <c:if test="${not empty Error_strUsername}">
                <span class="error">${Error_strUsername}</span>
            </c:if>

            <label>Password:</label>
            <input type="password" name="password"/>
            <c:if test="${not empty Error_strPassword}">
                <span class="error">${Error_strPassword}</span>
            </c:if>

            <label>Email:</label>
            <input type="text" name="email" value="${param.email}"/>
            <c:if test="${not empty Error_strEmail}">
                <span class="error">${Error_strEmail}</span>
            </c:if>

            <label>Phone:</label>
            <input type="text" name="phone" value="${param.phone}"/>
            <c:if test="${not empty Error_strPhone}">
                <span class="error">${Error_strPhone}</span>
            </c:if>

            <label>Address:</label>
            <input type="text" name="address" value="${param.address}"/>
            <c:if test="${not empty Error_strAddress}">
                <span class="error">${Error_strAddress}</span>
            </c:if>

            <input type="submit" value="Register"/>
            <p><a href="login.jsp">Click here to login</a></p>
        </form>


    </body>
</html>
