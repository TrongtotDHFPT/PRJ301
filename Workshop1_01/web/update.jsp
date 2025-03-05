<%@page import="dto.StartupProjectDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Project Status</title>
    </head>
    <body>
        <%
            StartupProjectDTO project = (StartupProjectDTO) request.getAttribute("project");
            String message = request.getAttribute("message")+"";
            message = (message.equals("null"))?"" : message;
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Project Name</th>
                    <th>Description</th>
                    <th>Old Status</th>
                    <th>Estimated Launch</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%= project.getProject_name() %></td>
                    <td><%= project.getDescription() %></td>
                    <td><%= project.getStatus() %></td>
                    <td><%= project.getEstimated_launch().toString() %></td>
                </tr>
            </tbody>
        </table>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="updateStatus"/>
            <input type="hidden" name="project_id" value="<%= project.getProject_id() %>"/>
            Update Status: <input type="text" name="status" required/>
            <input type="submit" value="Update"/>
            <p style="color: red;"><%= message %></p>   
        </form>
            <a href="MainController?action=search">Come back Project Dashboard</a>
    </body>
</html>