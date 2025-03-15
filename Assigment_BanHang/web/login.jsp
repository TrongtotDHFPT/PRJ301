<%-- 
    Document   : login
    Created on : Mar 15, 2025, 8:56:45 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="LoginServlet" method ="post">
            <input type="hidden" name ="action" value="login"/>
            User Name : <input  type="text" name="txtUsername" value=""/><br>
            Password  : <input  type="password" name="txtPassword" value=""/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
