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
        <link rel="stylesheet" href="css/login.css">

    </head>
    <body>
        <div class="login-container">
            <h2>Login Form</h2>
            <form action="login" method="post">
                <input type="text" name="txtUsername" placeholder="User Name" required />
                <input type="password" name="txtPassword" placeholder="Password" required />

                <c:if test="${not empty message}">
                    <h3>${message}</h3>
                </c:if>

                <input type="submit" value="Login"/>
            </form>

            <a href="home?search=">Coutinues without login</a>
            <a href="register.jsp">Register</a>
        </div>
    </body>
</html>

