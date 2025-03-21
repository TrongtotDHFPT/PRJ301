<%-- 
    Document   : login
    Created on : Mar 15, 2025, 8:56:45 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h2>Login Form</h2>
        <form action="login" method="post">
            User Name: <input type="text" name="txtUsername"/><br>
            Password: <input type="password" name="txtPassword"/><br>
            <c:if test="${not empty message}">
                <h3>${message}</h3>
            </c:if>
            <input type="submit" value="Login"/>
        </form>
        <a href="home?search=">Try for guest</a>
        <a href="register.jsp">Register</a>
        
    </body>
</html>
