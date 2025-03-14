<%-- 
    Document   : login
    Created on : Mar 12, 2025, 11:34:15 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/login.css">
    </head>
    <body>
        
        <form action="ControllerLogin" method="post">
            <input type="hidden" name="action" value="login"/>
            User Name :  <input type="text" name="txtUsername" value=""/> <br>
            Password :  <input type="password" name="txtPassword" value=""/> <br>
            <%
                String message = request.getAttribute("message")+"";
                message = (message.equals("null")) ? "" : message;
            %>
            <h3 style="color: red"><%=message%> </h3>
            <input type="submit" value="Login"/>
        </form>
        
        
    </body>
</html>
