<%-- 
    Document   : search.jsp
    Created on : Mar 13, 2025, 12:15:20 AM
    Author     : trong
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h2>Create New Exam</h2>
<form action="ControllerLogin" method="post">
    <input type="hidden" name="action" value="createExam" />

    <label>Exam Title:</label>
    <input type="text" name="exam_title" required/><br/>

    <label>Subject:</label>
    <input type="text" name="subject" required/><br/>

    <label>Category:</label>
    <select name="category_id" required>
        <option value="">-- Select Category --</option>
        <c:forEach var="exCategory" items="${requestScope.list}">
            <option value="${exCategory.category_id}">${exCategory.category_name}</option>
        </c:forEach>
    </select><br/>

    <label>Total Marks:</label>
    <input type="number" name="total_marks" required/><br/>

    <label>Duration (minutes):</label>
    <input type="number" name="duration" required/><br/>

    <input type="submit" value="Create Exam" />
</form>
