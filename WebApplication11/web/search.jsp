<%-- 
    Document   : search
    Created on : Feb 20, 2025, 7:41:07 PM
    Author     : trong
--%>

<%@page import="dto.BookDTO"%>
<%@page import="java.util.List"%>
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
        <%
            if(session.getAttribute("user") != null){
            UserDTO user = (UserDTO)session.getAttribute("user");
        %>
        <h1>Hello <%=user.getFullName()%> </h1>
        <form action="MainController">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" value="Logout"/>
        </form>
        
        <%
            String searchTerm = request.getAttribute("searchTerm")+"";
            searchTerm =searchTerm.equals("null") ? "": searchTerm;
        %>
        <br/>
        <form action="MainController">
            <input type="hidden" name = "action" value = "search"> 
            Search Books : <input type="text" name="searchTerm" value ="<%=searchTerm%>"/> 
            <input type="submit" name="Search"/>
        </form>
        <%
             
             if(request.getAttribute("books") != null){
                 List<BookDTO>  books =(List<BookDTO>) request.getAttribute("books");
        %>
        
        <table class ="book-table" border = "1"> 
            <thead>
                <tr>
                    <th>BookID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>PublishYear</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
                
            </thead>
            
            <tbody>
                <%
                    for(BookDTO b : books){
                %>
                <tr>
                     <td><%=b.getBookID()%></td>
                     <td><%=b.getTitle()%></td>
                     <td><%=b.getAuthor()%></td>
                     <td><%=b.getPublishYear()%></td>
                     <td><%=b.getPrice()%></td>
                     <td><%=b.getQuantity()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%      }
            }else{%>
            You do not have permission to access this content!
            <%}%>
        <br/>
        
        
    </body>
</html>
