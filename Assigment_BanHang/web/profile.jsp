<%-- 
    Document   : profile
    Created on : Mar 16, 2025, 3:54:18 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
    </head>
    <body>
        <c:set var="user" value="${sessionCope.user}"></c:set>
        <c:if test="${not empty requestScope.user}">
            
            
            
        </c:if>
        <c:if test="${empty sessionCope.user}">
            <h3 style="color: red">Please login!</h3>
        </c:if>
        
        
    </body>
</html>
