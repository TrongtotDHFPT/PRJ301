<%-- 
    Document   : search.jsp
    Created on : Mar 13, 2025, 12:15:20 AM
    Author     : trong
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
//            UserDTO user = (UserDTO) request.getAttribute("user");

        %>
        <c:if test="${ not empty sessionScope.user}">
            <c:set var="user" value="${sessionScope.user}"/>
            <h1>Hello ${user.name}</h1>


            <form action="ControllerLogin" >
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" value="Logout"/>
            </form>
            
            


            <table border = 1>
                <thead >
                <th>Category Name</th>
                <th>Description</th>
                
            </thead>

            <tbody>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>

            </tbody>

        </table>







    </c:if>
    <c:if test="${empty sessionScope.user}">
        <p>
            Please 
            <a href="login.jsp"  style="color: red"></a>
            to access this page!
        </p>
    </c:if>



    <h1></h1>
</body>
</html>
