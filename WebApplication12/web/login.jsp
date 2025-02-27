<%-- 
    Document   : login
    Created on : Feb 20, 2025, 6:48:51 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
            margin: 0;
        }
        .login-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .login-container h2 {
            margin-bottom: 20px;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 95%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .login-container .submit {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-left: 4px;
        }
        .login-container .submit:hover {
            background-color: #0056b3;
        }
    </style>
    </head>

    <body>
        <div class="login-container">
        <h2>Login</h2>
        <%
            String message = request.getAttribute("message")+"";
            message = message.equals("null") ?"": message;
        %>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login" />
            <input type="text" name="txtUserID" placeholder="User ID" required /><br/>
            <input type="password" name="txtPassword" placeholder="Password" required /> <br/>
            <h1 style="color :red;font-size: 15px;"><%=message!=null ? message :""%><h1/> 
            <input class="submit" type="submit" value="Login" />
        </form>
        </div>
    </body>
</html>
