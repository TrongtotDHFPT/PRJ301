<%-- 
    Document   : login
    Created on : Feb 26, 2025, 8:37:42 PM
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
            <input type="hidden" name ="action" value="login"/>
            UserID<input type="text" name="txtUserID"/><br/>
            Password<input type="password" name="txtPassword"/><br/>
            <input type="submit" value="login"/>
        </form>
        <%
            String message = request.getAttribute("message")+"";
            message = message.equals("null") ?"": message;
        %>
        <%=message!=null ? message :""%>
    </body>
    
    
</html>
