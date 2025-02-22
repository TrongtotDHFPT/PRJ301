<%-- 
    Document   : login
    Created on : Feb 20, 2025, 6:48:51 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login" />
            UserID <input type="text" name="txtUserID"/><br/>
            Password <input type="password" name="txtPassword"/><br/>
            <input type="submit" value="Login"/>
        </form>
        <%
            String message =  request.getAttribute("message")+"";
        %>
        <%=message.equals("null") ?"" :message%>
    </body>
</html>
