<%-- 
    Document   : search
    Created on : Feb 20, 2025, 7:41:07 PM
    Author     : trong
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        String message = (String)request.getAttribute("message");
        UserDTO user = (UserDTO)request.getAttribute("user");
    %>
    <body> 
        
        <h1>Hello <%=user.getFullName()%> </h1>
        
        
        <form action="MainController">
            <input type="hidden" name = "action" value = "search"> 
            Search Value: <input type="text" name="searchTerm"/> 
            <input type="submit" name="Search"/>
        </form>
        <form action="#">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" value="Logout"/>
        </form>
        
        <br/>
    </body>
</html>
