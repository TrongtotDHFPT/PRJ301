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
                <style>
            .book-table {
                width: 100%;
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 14px;
                font-family: Arial, sans-serif;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }

            .book-table thead th {
                background-color: #009879;
                color: #ffffff;
                text-align: left;
                font-weight: bold;
                padding: 12px 15px;
            }

            .book-table tbody tr {
                border-bottom: 1px solid #dddddd;
            }

            .book-table tbody tr:nth-of-type(even) {
                background-color: #f3f3f3;
            }

            .book-table tbody tr:last-of-type {
                border-bottom: 2px solid #009879;
            }

            .book-table tbody td {
                padding: 12px 15px;
            }

            .book-table tbody tr:hover {
                background-color: #f5f5f5;
                transition: 0.3s ease;
            }
            
           </style> 
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
                    <th>Action</th>
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
                     <td><a href="MainController?action=delete&id=<%=b.getBookID()%>&searchTerm=<%=searchTerm%>"> 
                             <img src ="assets/images/Trash-can-icon.png" style="height: 25px;margin-left: 16px;">
                         </a>
                     </td>
                </tr>
                <%  } %>
            </tbody>
        </table>
        <%      }
            }else{%>
            You do not have permission to access this content!
            <%}%>
        <br/>
        
        
    </body>
</html>
