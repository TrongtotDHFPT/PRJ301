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
            String project_id = request.getAttribute("project") + "";
            StartupProjectDTO project = (StartupProjectDTO) request.getAttribute("project");
        %>
        <h1>Update Status Of Project</h1>
        <h1><%=project.toString()%></h1>
        <h1><%=project_id%></h1>
        Ideation
        Development
        Launch
        Scaling
        <form action="MainController">
            <input type="hidden" name="action" value="updateStatus"/>
            Project ID :<input type="number" name="project_id" value ="<%=project.getProject_id()%>"/> <br/>
            Update Status:<input type="text" name="status"/>
            <input type="submit" value="Update">
        </form>

    </body>
</html>
