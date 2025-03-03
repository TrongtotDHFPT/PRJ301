<%-- 
    Document   : login
    Created on : Mar 3, 2025, 8:02:27 PM
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
        <form action="MainController">
            <input type="hidden" name="action" value="login"/>
            UserID : <input type="text" name="txtUserID"/><br/>
            Password : <input type="text" name="txtPassword"/><br/> 
            <input type="submit" value="login"/>
              
        </form>
    </body>
</html>
