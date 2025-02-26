<%-- 
    Document   : search
    Created on : Feb 26, 2025, 9:13:43 PM
    Author     : trong
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
        <%
            if(session.getAttribute("user")!=null){
              UserDTO user = (UserDTO)session.getAttribute("user");
         
        %>
       
        <form action="MainController">
            <input type="hidden" name = "action" value ="logout"/>
            <input type="submit" value="logout"/> 
        </form>
        <br/>
        
        <%
            String searchTerm = request.getAttribute("searchTerm")+"";
            searchTerm = searchTerm.equals("null") ? "" : searchTerm;
        %>
        <form action="MainController">
            <input type="hidden" name = "action" value ="search"/>
            Search Books :<input type="text" name="searchTerm" value ="<%=searchTerm%>"/>
            <input type="submit" value="search"/> 
        </form>
            
        <%
            if(request.getAttribute("books") != null){
                List<BookDTO> books = (List<BookDTO>)request.getAttribute("books");
        %>
        <table border = 1>
            <thead>
                <tr>
                    <th>bookID</th>
                    <th>title</th>
                    <th>author</th>
                    <th>publishYear</th>
                    <th>price</th>
                    <th>quantity</th>
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
                    <td>
                        <a href="MainController?action=delete&id=<%=b.getBookID()%>&searchTerm=<%=searchTerm%>">
                            <img src="assets/images/Martz90-Circle-Trash.512.png" style="height: 25px;margin-left: 14px;"/>
                        </a>
                    </td>                   
                </tr>
                <%}%>
            </tbody>
            
        </table>
    </body>
        <%}%>
    <%}else{%>
        You do not have permission to access this content.
    <%}%>
</html>
