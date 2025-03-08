<%-- 
    Document   : login
    Created on : Feb 27, 2025, 9:53:55 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Định dạng toàn bộ trang */
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            /* Container của form */
            .login-container {
                background: white;
                padding: 25px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                text-align: center;
                width: 350px;
            }

            /* Tiêu đề "User Login" */
            .login-container h2 {
                color: #333;
                margin-bottom: 20px;
            }

            /* Ô nhập liệu */
            .login-container input[type="text"],
            .login-container input[type="password"] {
                width: 90%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
            }

            /* Placeholder màu xám nhạt */
            .login-container input::placeholder {
                color: #aaa;
            }

            /* Nút Login */
            .login-container .submit {
                width: 100%;
                background-color: #007bff;
                color: white;
                border: none;
                padding: 10px;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                margin-top: 10px;
                transition: 0.3s;
            }

            /* Hiệu ứng hover cho nút Login */
            .login-container .submit:hover {
                background-color: #0056b3;
            }

            /* Hiển thị thông báo lỗi */
            .login-container h1 {
                color: red;
                font-size: 14px;
                margin-top: 10px;
            }


        </style>
    </head>

    <body>
        <div class="login-container">
            <h2>User Login</h2>
            <%
                String message = request.getAttribute("message") + "";
                message = message.equals("null") ? "" : message;
            %>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="login" />
                <input type="text" name="txtUserID" placeholder="User ID" required /><br/>
                <input type="password" name="txtPassword" placeholder="Password" required /> <br/>

                <% if (!message.isEmpty()) {%>
                <h1><%= message%></h1>
                <% }%>

                <input class="submit" type="submit" value="Login" />
            </form>
        </div>

    </body>
</html>
