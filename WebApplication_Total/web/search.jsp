<%-- 
    Document   : search
    Created on : Feb 24, 2025, 8:40:58 PM
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
            if(session.getAttribute("user")!= null){
            UserDTO user = (UserDTO) session.getAttribute("user");
        %>
        <h1>Hello <%=user.getFullName()%></h1>
        <form action="MainController">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" value="Logout"/>
        </form>
        <br/>
        
        <%
//            Xử lí searchTerm
            String searchTerm = request.getAttribute("searchTerm")+"";
            searchTerm = searchTerm.equals("null") ? "" : searchTerm;
        %>
        <form action="MainController" method="get">
            <input  type="hidden" name="action" value="search"/>
            Search Books : <input type="text" name ="searchTerm"  vale ="<%=searchTerm%>"/>
            <input type="submit" value="Search"/>
        </form>
        <%
            if(request.getAttribute("list") != null){
            List<BookDTO> list = (List<BookDTO>)request.getAttribute("list");
        %>
            <table border = 1>
                <thead>
                    <tr>
                        <th>BookID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>PublishYear</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action </th>
                    </tr>
                </thead>
                
                <tbody>
                    <%
                        for(BookDTO b : list){
                    %>
                    <tr>
                        <td><%=b.getBookID()%></td>
                        <td><%=b.getTitle()%></td>
                        <td><%=b.getAuthor()%></td>
                        <td><%=b.getPublishYear()%></td>
                        <td><%=b.getPrice()%></td>
                        <td><%=b.getQuantity()%></td>
                        <td>
                            <a href="MainController?action=delete
                               &id=<%=b.getBookID()%>
                               &searchTerm=<%=searchTerm%>">
                                <img src="assets/images/Martz90-Circle-Trash.512.png" 
                                     style="height : 25px;margin-left: 12px;">
                            </a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <%  }
                }else{ %>
                You do not have permission to access this content! 
        <%}%>
    </body>
</html>
