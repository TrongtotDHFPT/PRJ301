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
//            String project_id =  request.getAttribute("project_id")+"";
            StartupProjectDTO project = (StartupProjectDTO) request.getAttribute("project");
            
        %>
        <table border = 1>
            <thead>
                <th>Project Name</th>
                <th>Description</th>
                <th>Old Status</th>
                <th>Estimated Launch</th>
            </thead>


        <tbody>
            <td><%= project.getProject_name()%></td>
            <td><%= project.getDescription()%></td>
            <td><%= project.getStatus()%></td>
            <td><%= project.getEstimated_launch().toString()%></td>
        </tbody>
</table>
        <form action="MainController">
            <input type="hidden" name="action" value="updateStatus"/>
            <input type="hidden" name = "project_id" value="<%=project.getProject_id()%>"/>
            Update Status:<input type="text" name="status"/>
            <input type="submit" value="Update">
        </form>


</body>
</html>
