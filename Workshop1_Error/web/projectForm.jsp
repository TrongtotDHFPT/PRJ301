<%-- 
    Document   : bookForm
    Created on : Mar 2, 2025, 9:44:34 PM
    Author     : trong
--%>

<%@page import="java.sql.Date"%>
<%@page import="dto.StartupProjectDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Dự Án</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="date"] {
            width: 95%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }
        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
        .btn-submit {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }
        .btn-submit:hover {
            background-color: #0056b3;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Create Project</h2>

        <%
            String message = request.getAttribute("message") + "";
            message = message.equals("null") ? "" : message;
            String txt_projectName_Error = request.getAttribute("txt_projectName_Error") + "";
            txt_projectName_Error = txt_projectName_Error.equals("null") ? "" : txt_projectName_Error;
            String txt_statusInValid_Error = request.getAttribute("txt_statusInValid_Error") + "";
            txt_statusInValid_Error = txt_statusInValid_Error.equals("null") ? "" : txt_statusInValid_Error;
            String txt_dateError = request.getAttribute("txt_dateError") + "";
            txt_dateError = txt_dateError.equals("null") ? "" : txt_dateError;
            
            String projectName = request.getAttribute("projectName") + "";
            projectName = projectName.equals("null") ? "" : projectName;
            String description = request.getAttribute("description") + "";
            description = description.equals("null") ? "" : description;
            String status = request.getAttribute("status") + "";
            status = status.equals("null") ? "" : status;
            String launchDate = request.getAttribute("launchDate") + "";
            launchDate = launchDate.equals("null") ? "" : launchDate;
        %>

        <form action="MainController">
            <input type="hidden" name="action" value="create"/>

            <div class="form-group">
                <label for="txtProjectName">Project Name:</label>
                <input type="text" id="txtProjectName" name="txtProjectName" value="<%= projectName %>"/>
                <p class="error-message"><%= txt_projectName_Error %></p>
            </div>

            <div class="form-group">
                <label for="txtDescription">Description:</label>
                <input type="text" id="txtDescription" name="txtDescription" value="<%= description %>"/>
            </div>

            <div class="form-group">
                <label for="txtStatus">Status:</label>
                <input type="text" id="txtStatus" name="txtStatus" value="<%= status %>"/>
                <p class="error-message"><%= txt_statusInValid_Error %></p>
            </div>

            <div class="form-group">
                <label for="txtDate">Launch Date:</label>
                <input type="date" id="txtDate" name="txtDate" value="<%= launchDate %>"/>
                <p class="error-message"><%= txt_dateError %></p>
            </div>

            <button type="submit" class="btn-submit">Save</button>

            <p class="error-message"><%= message %></p>
        </form>
        <a href="MainController?action=search&seachTerm=" class="back-link">Come back Project Dashboard </a>
    </div>

</body>
</html>

