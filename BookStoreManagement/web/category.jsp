<%-- 
    Document   : category
    Created on : Mar 26, 2025, 6:17:56 PM
    Author     : trong
--%>

<%@page import="utils.AuthUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Category </title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%
            if (AuthUtils.isAdmin(session)) {
        %>    
        
        <form action="addCategory">
            <input type="hidden" name="action" value="addCate"/>
            Add new category:<input type="text" name="category_name" value="${not empty category_name ? category_name : ''}"/>
            <input type="submit" value="Add"/>
        </form>
            <c:if test="${not empty message}">
                <p>${message}</p>
            </c:if>
        <c:if test="${not empty cateList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Category ID</th>
                        <th>Category Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cate" items="${cateList}">
                    <tr>
                        <td>${cate.category_id}</td>
                        <td>${cate.category_name}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <%}%>
        <%@include file="footer.jsp" %>
    </body>
</html>
