<%-- 
    Document   : search
    Created on : Feb 27, 2025, 11:10:13 PM
    Author     : trong
--%>


<%@page import="utils.AuthUtils"%>
<%@page import="java.util.List"%>
<%@page import="dto.StartupProjectDTO"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                display: flex;
                flex-direction: column;
                align-items: center;
                padding: 20px;
            }
            .container {
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 80%;
                max-width: 900px;
                text-align: center;
            }
            h1 {
                color: #333;
            }
            .form-group {
                margin: 10px 0;
            }
            input[type="text"] {
                padding: 8px;
                width: 60%;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            input[type="submit"], .btn-action {
                padding: 8px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 14px;
            }
            .btn-logout {
                background-color: #dc3545;
                color: white;
            }
            .btn-search {
                background-color: #007bff;
                color: white;
            }
            .btn-create {
                background-color: #28a745;
                color: white;
                padding: 10px;
                text-decoration: none;
                border-radius: 5px;
                display: inline-block;
                margin-bottom: 10px;
            }
            .btn-action {
                background-color: #ffc107;
                color: black;
                text-decoration: none;
            }
            .btn-logout:hover {
                background-color: #c82333;
            }
            .btn-search:hover {
                background-color: #0056b3;
            }
            .btn-create:hover {
                background-color: #218838;
            }
            .btn-action:hover {
                background-color: #e0a800;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: center;
            }
            th {
                background-color: #007bff;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            .login-message {
                color: red;
                font-weight: bold;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: center; /* Căn giữa nội dung cột */
                vertical-align: middle; /* Căn giữa theo chiều dọc */
            }

            th {
                background-color: #007bff;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            /* Căn chỉnh lại nút Update Status */
            .action-cell {
                width: 150px; /* Đảm bảo không bị tràn cột */
                text-align: center;
            }

            .btn-action {
                display: inline-block;
                background-color: #ffc107;
                color: black;
                text-decoration: none;
                padding: 5px 10px;
                border-radius: 5px;
                font-size: 14px;
                font-weight: bold;
                border: none;
                cursor: pointer;
                transition: 0.3s;
            }

            .btn-action:hover {
                background-color: #e0a800;
            }
            
        </style>
    </head>

    <body>
        <%
            if (session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
        %>

        <div class="container">
            <h1>Hello, <%= user.getName()%> 👋</h1>

            <!-- Logout Form -->
            <form action="MainController" class="form-group">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" value="Logout" class="btn-logout"/>
            </form>

            <%
                if (AuthUtils.isLoggedIn(session)) {
                    if (AuthUtils.isFounder(session)) {
            %>

            <!-- Create Project Button -->
            <a href="projectForm.jsp" class="btn-create">Create New Project</a>

            <%
                String searchTerm = request.getAttribute("searchTerm") + "";
                searchTerm = searchTerm.equals("null") ? "" : searchTerm;
            %>

            <!-- Search Form -->
            <form action="MainController" class="form-group">
                <input type="hidden" name="action" value="search"/>
                <input type="text" name="txtsearchTerm" placeholder="Search Project..." value="<%= searchTerm%>"/>
                <input type="submit" value="Search" class="btn-search"/>
            </form>

            <%  }
                } %>

            <%
                if (request.getAttribute("list") != null) {
                    List<StartupProjectDTO> list = (List<StartupProjectDTO>) request.getAttribute("list");
            %>

            <!-- Projects Table -->
            <table>
                <thead>
                    <tr>
                        <th>Project Name</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Estimated Launch</th>
                            <% if (AuthUtils.isLoggedIn(session) && AuthUtils.isFounder(session)) { %>
                        <th>Action</th>
                            <% } %>
                    </tr>
                </thead>
                <tbody>
                    <% for (StartupProjectDTO project : list) {%>
                    <tr>
                        <td><%= project.getProject_name()%></td>
                        <td><%= project.getDescription()%></td>
                        <td><%= project.getStatus()%></td>
                        <td><%= project.getEstimated_launch().toString()%></td>
                        <% if (user.getRole().equals("Founder")) {%>
                        <td>
                            <a href="MainController?action=update&project_id=<%= project.getProject_id()%>" class="btn-action">Update Status</a>
                        </td>
                        <% } %>
                    </tr>
                    <% } %>
                </tbody>
            </table>

            <% } %>
        </div>

        <% } else { %>
        <p class="login-message">Please <a href="login.jsp">login</a> to access this page.</p>
        <% }%>

    </body>
</html>
