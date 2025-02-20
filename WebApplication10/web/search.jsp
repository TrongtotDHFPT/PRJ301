<%-- 
    Document   : search
    Created on : Feb 13, 2025, 11:27:20 AM
    Author     : tungi
--%>

<%@page import="dto.BookDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> 
        <%@include file="header.jsp" %> 
        <%               
                if(session.getAttribute("user")!=null){
                  UserDTO user = (UserDTO) session.getAttribute("user");
        %>
        <h1> Welcome <%=user.getFullName()%> </h1>
        <form action="MainController">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" value="Logout"/>
        </form>
        
        <br/>
        
        <form action="MainController">
            <input type="hidden" name = "action" value = "search"> 
            Search Value: <input type="text" name="searchTerm"/> 
            <input type="submit" name="Search"/>
        </form>
        <%
            if(request.getAttribute("listBooks") != null){
                List<BookDTO> listBooks = (List<BookDTO>)request.getAttribute("listBooks");
            }
        %>
        
        //table
        
        
        <%
            }else{
        %>
            You do not have permission to access this content.
        <%}
        %>
    </body>
</html>