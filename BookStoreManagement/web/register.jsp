<%-- 
    Document   : register
    Created on : Mar 16, 2025, 1:49:47 PM
    Author     : trong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>

        <form action="register" method="post">
            Name: <input type="text" name="name" value="${param.name}"/><br>
            <c:if test="${not empty Error_strName}">
                <span style="color: red;">${Error_strName}</span><br>
            </c:if>

            Username: <input type="text" name="username" value="${param.username}"/><br>
            <c:if test="${not empty Error_strUsername}">
                <span style="color: red;">${Error_strUsername}</span><br>
            </c:if>

            Password: <input type="password" name="password"/><br>
            <c:if test="${not empty Error_strPassword}">
                <span style="color: red;">${Error_strPassword}</span><br>
            </c:if>

            Email: <input type="text" name="email" value="${param.email}"/><br>
            <c:if test="${not empty Error_strEmail}">
                <span style="color: red;">${Error_strEmail}</span><br>
            </c:if>
                
            Phone: <input type="text" name="phone" value="${param.phone}"/><br>
            <c:if test="${not empty Error_strPhone}">
                <span style="color: red;">${Error_strPhone}</span><br>
            </c:if>

            Address: <input type="text" name="address" value="${param.address}"/><br>
            <c:if test="${not empty Error_strAddress}">
                <span style="color: red;">${Error_strAddress}</span><br>
            </c:if>

            <input type="submit" value="Register"/><br><br>
            <p><a href="login.jsp">Click here to login</a></p>
            <c:if test="${not empty message}">
                <p style="color: green;">${message}</p>
                
            </c:if>
        </form>
            



    </body>
</html>
