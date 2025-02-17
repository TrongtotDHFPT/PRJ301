<%-- 
    Document   : search
    Created on : Feb 13, 2025, 7:21:00 PM
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
    <body>
        <%
            UserDTO user = (UserDTO)request.getAttribute("user");
        %>
        <h1>Hello <%=user.getFullName()%></h1>
        
        <form action="#">
            Search value :<input type="submit" name = "txtSearchValue"/> <br/>
            <input type="submit" name = "search"/>
        </form>
    </body>
</html>
