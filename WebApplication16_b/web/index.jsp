<%-- 
    Document   : index
    Created on : Mar 13, 2025, 10:55:59 AM
    Author     : trong
--%>

<%@page import="dao.BookDAO"%>
<%@page import="dto.BookDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hiển thị hết các sản phẩm !</h1>
        <%
            BookDAO bdao = new BookDAO();
            List<BookDTO> books = bdao.readAll();
        %>


   <%
       if(books != null){
   %>
        <table class="book-table" border = 1>
            <thead>
                <tr>
                    <th>BookID</th>
                    <th>Image</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>PublishYear</th>
                    <th>Price</th>
                    <th>Quantity</th>
            <c:if test ="${isAdmin}">
                <th>Action</th>
            </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="b" items = "${requestScope.books}">
                <tr>
                    <td>${b.bookID}</td>
                    <td><img src="${b.image}" width="150px" /></td>
                    <td>${b.title}</td>
                    <td>${b.author}</td>
                    <td>${b.publishYear}</td>
                    <td>${b.price}</td>
                    <td>${b.quantity}</td>

                <c:if test ="${isAdmin}"> 
                    <td>
                        <a href="MainController?action=edit&id=${b.bookID} &searchTerm=${searchTerm}"">
                            <img src="assets/images/Edit-icon.png" style="height: 25px"/>
                        </a>
                        <a href="MainController?action=delete&id=${b.bookID}&searchTerm=${searchTerm}">
                            <img src="assets/images/Trash-can-icon.png" style="height: 25px"/>
                        </a>
                    </td>
                </c:if>  
                </tr>
            </c:forEach>
            </tbody>
        </table>
    
        <%}%>


</body>
</html>
