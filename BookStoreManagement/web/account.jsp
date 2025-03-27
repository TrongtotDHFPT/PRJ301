<%-- 
    Document   : account.jsp
    Created on : Mar 26, 2025, 11:47:52 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Account</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/account.css">

    </head>
    <body>
        <br><br><br>
        <%@include file="header.jsp" %>
        <h1>Hồ sơ cá nhân</h1>
        <c:if test="${not empty user}">
            <form action="account" method="post">
                <input type="hidden" name="action" value="changeInfor"/>

                <label for="user_id">User ID:</label>
                <input type="text" id="user_id" name="user_id" value="${user.user_id}" readonly=""><br>

                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${user.name}" required><br>
                <c:if test="${not empty Error_strName}">
                    <span style="color: red;">${Error_strName}</span><br>
                </c:if>

                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="${user.username}" required readonly=""><br>

                <label for="role">Role:</label>
                <input type="text" id="role" name="role" value="${user.role}" required readonly=""><br>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.email}"><br>
                <c:if test="${not empty Error_strEmail}">
                    <span style="color: red;">${Error_strEmail}</span><br>
                </c:if>

                <label for="phone">Phone:</label>
                <input type="tel" id="phone" name="phone" value="${user.phone}" required><br>
                <c:if test="${not empty Error_strPhone}">
                    <span style="color: red;">${Error_strPhone}</span><br>
                </c:if>

                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${user.address}" required><br>
                <c:if test="${not empty Error_strAddress}">
                    <span style="color: red;">${Error_strAddress}</span><br>
                </c:if>

                <button type="submit">Save</button>

                <c:if test="${not empty message}">
                    <p style="color: green;">${message}</p>
                </c:if>
            </form>
        </c:if>
        <%@include file="footer.jsp" %>
    </body>
</html>
