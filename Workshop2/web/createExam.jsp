<%-- 
    Document   : createExam
    Created on : Mar 14, 2025, 2:14:35 PM
    Author     : trong
--%>

<%@page import="utils.AuthUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/createExam.css">
    </head>
    <body> 
        <c:set var="isInstructor" value="<%=AuthUtils.isInstructor(session)%>"></c:set>
        
        <c:if test="${isInstructor}">
        <form action="ControllerLogin" method="post">
            <h1>Create New Exam</h1>
            <c:set var="exam" value="${requestScope.exam}"/>
            <input type="hidden" name="action" value="createExam"/>

            Exam Title: <input type="text" name="exam_title" value="${exam.exam_title}"/>
            <c:if test="${not empty requestScope.Error_exam_title}">
                <p style="color: red">${requestScope.Error_exam_title}</p>
            </c:if><br>

            Subject: <input type="text" name="subject" value="${exam.subject}"/> 
            <c:if test="${not empty requestScope.Error_subject}">
                <p style="color: red">${requestScope.Error_subject}</p>
            </c:if><br>

            Categor Name
            <select name="category_id" >
                <option value="">--Categor--</option>
                <c:forEach var="category" items="${requestScope.list}">
                    <option value="${category.category_id}">${category.category_name}</option>
                </c:forEach>
            </select>
            <c:if test="${not empty requestScope.Error_category_id}">
                <p style="color: red">${requestScope.Error_category_id}</p>
            </c:if>
            <br>

            Total Marks: <input type="number" name="total_marks" value="${exam.total_marks}"/>
            <c:if test="${not empty requestScope.Error_total_marks}">
                <p style="color: red">${requestScope.Error_total_marks}</p>
            </c:if><br>

            Duration(minutes): <input type="number" name="duration" value="${exam.duration}"/>
            <c:if test="${not empty requestScope.Error_duration}">
                <p style="color: red">${requestScope.Error_duration}</p>
            </c:if><br>


            <input type="submit" value="Create"/>
            <c:if test="${not empty requestScope.message}">
                <p style="color: green">${requestScope.message}</p>
            </c:if>
            <a href="ControllerLogin?action=search" class="button"> Back the search page!</a>
        </form>
        </c:if>
        <c:if test="${!isInstructor}">
            
            <h3 style="color: red">You dont have permission to access this page!</h3>    
            <p>Please <a href="login.jsp"  style="color: red"> login</a>
            to access this page!
            </p>
        </c:if>
        

    </body>
</html>
