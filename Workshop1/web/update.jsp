<%-- 
    Document   : update
    Created on : Mar 3, 2025, 10:00:28 PM
    Author     : trong
--%>

<%@page import="dto.StartupProjectDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            StartupProjectDTO project = (StartupProjectDTO) request.getAttribute("project");
            String message = request.getAttribute("message")+"";
            message = (message.equals("null"))?"" : message;
            String status = request.getAttribute("status")+"";
            status = (status.equals("null"))?"" : status;
        %>
        <table border = 1>
            <thead>
                <th>Project Name</th>
                <th>Description</th>
                <th>Status</th>
                <th>Estimated Launch</th>
            </thead>
            <tbody>
                <td><%= project.getProject_name()%></td>
                <td><%= project.getDescription()%></td>
                <td  style="color:red;"><%= project.getStatus()%></td>
                <td><%= project.getEstimated_launch().toString()%></td>
            </tbody>
</table>
        <br/>
        <br/>
        <form action="MainController">
            <input type="hidden" name="action" value="updateStatus"/>
            <input type="hidden" name = "project_id" value="<%=project.getProject_id()%>"/>
            Update Status:<input type="text" name="status" value="<%=status%>"/>
            <input type="submit" value="Update">
        </form>
        <h3 style="color:red;"><%=message%></h3>
        <br/>
        <br/>
            <a href="MainController?action=search">Come back Project Dashboard</a>


</body>
</html>
