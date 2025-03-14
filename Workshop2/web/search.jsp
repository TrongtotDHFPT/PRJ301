<%-- 
    Document   : search.jsp
    Created on : Mar 13, 2025, 12:15:20 AM
    Author     : trong
--%>

<%@page import="utils.AuthUtils"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/search.css">
    </head>
    <body>
        <c:set var="isInstructor" value="<%=AuthUtils.isInstructor(session)%>"></c:set>
        <c:set var="isStudent" value="<%=AuthUtils.isStudent(session)%>"></c:set>
        <c:set var="isLoggedIn" value="<%=AuthUtils.isLoggedIn(session)%>"/>
        <c:if test="${ not empty sessionScope.user}">
            <c:set var="user" value="${sessionScope.user}"/>
            <h1>Hello ${user.name}</h1>
            <c:if test="${isLoggedIn}">            
                <form action="ControllerLogin" >
                    <input type="hidden" name="action" value="logout"/>
                    <input type="submit" value="Logout"/>
                </form>
            </c:if>
            <c:if test="${isInstructor}">
                <a href="ControllerLogin?action=goToCreateExam" class="button">Create new exam</a>
            </c:if>


            <c:if test="${isStudent}">
                <form action="ControllerLogin" method="post">
                    <input type="hidden" name="action" value="filter"/>

                    <select name ="category_id">
                        <option value=""> Filter Exams By Category</option>
                        <c:forEach var="exCategory" items="${requestScope.list}">
                            <option value="${exCategory.category_id}">${exCategory.category_name}</option>
                            <c:if test="${param.category_id == exCategory.category_id}">selected="selected"</c:if>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Filter"/>
                </form>
                <c:if test="${not empty requestScope.message_Filter}"><h3 style="color: red">${requestScope.message_Filter}</h3></c:if>
            </c:if>

            <br>
            <table border = 1>
                <thead >
                    <tr>

                        <th>Category Name</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <c:forEach var ="examCategory" items="${requestScope.list}">
                    <tbody>
                        <tr>

                            <td>${examCategory.category_name}</td>
                            <td>${examCategory.description}</td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>

        </c:if>

        <c:if test="${not empty requestScope.listExamDTO}">

            <h3>Exam List ${requestScope.category_name}</h3>
            <table border = 1>
                <thead >
                    <tr>
                        <th>Exam Title</th>
                        <th>Subject</th>
                        <th>Total Marks</th>
                        <th>Duration  (mins)</th>
                    </tr>
                </thead>
                <c:forEach var ="exam" items="${requestScope.listExamDTO}">
                    <tbody>
                        <tr>
                            <td>${exam.exam_title}</td>
                            <td>${exam.subject}</td>
                            <td>${exam.total_marks}</td>
                            <td>${exam.duration}</td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>

        </c:if>
        <c:if test="${empty sessionScope.user}">
            <p>
                Please 
                <a href="login.jsp"  style="color: red"> login</a>
                to access this page!
            </p>
        </c:if>
    </body>
</html>
