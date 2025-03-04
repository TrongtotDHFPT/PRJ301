<%-- 
    Document   : search
    Created on : Feb 27, 2025, 11:10:13 PM
    Author     : trong
--%>

<%@page import="java.util.List"%>
<%@page import="dto.StartupProjectDTO"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>

    <body>
        <%
            if (session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
        %>
        <h1>Hello <%= user.getName()%> </h1> <br/>
        <form action="MainController">
            <input type="hidden" name="action" value="logout"/>
            <input type="submit" value="Logout"/>
        </form>
        <br/>
        <%
                if (user.getRole().equals("Founder")) {
        %>
        <a href="bookForm.jsp">Create New Project</a>
        <%
                String searchTerm = request.getAttribute("searchTerm") + "";
                searchTerm = searchTerm.equals("null") ? "" : searchTerm;
        %>
        <form action="MainController">
            <input type="hidden" name="action" value="search"/>
            Search Project: <input type="text" name="txtsearchTerm" value="<%= searchTerm%>"/>
            <input type="submit" value="Search"/>
        </form>
        <br/>
        <%}%>

        <%
            if (request.getAttribute("list") != null) {
                List<StartupProjectDTO> list = (List<StartupProjectDTO>) request.getAttribute("list");
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Project Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Estimated Launch</th>
                        <%
                            if (user.getRole().equals("Founder")) {
                        %>
                    <th>Action</th>
                        <%}%>
                </tr>
            </thead>
            <tbody>
                <%
                    for (StartupProjectDTO project : list) {
                %>
                <tr>
                    <td><%= project.getProject_name()%></td>
                    <td><%= project.getDescription()%></td>
                    <td><%= project.getStatus()%></td>
                    <td><%= project.getEstimated_launch().toString()%></td>
                    <%
                        if (user.getRole().equals("Founder")) {
                    %>
                    <td>
                        <a href="MainController?action=update&project_id=<%=project.getProject_id()%>">Update Status</a>
                    </td>
                    <%}%>
                </tr>
                <%}%>
            </tbody>
        </table>
        <%}%>
        <%} else {%>
        <p>Please <a href="login.jsp">login</a> to access this page.</p>
        <%}%>
    </body>
</html>