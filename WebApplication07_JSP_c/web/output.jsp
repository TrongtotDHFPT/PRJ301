<%-- 
    Document   : output
    Created on : Feb 10, 2025, 11:28:06 AM
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
        <%
            String message = (String)request.getAttribute("Result");
           
        %>
        <h3 style="color:red;"> <%= message %> </h3>
    </body>
</html>
