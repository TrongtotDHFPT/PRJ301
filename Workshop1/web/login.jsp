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
<!--        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                background: linear-gradient(to right, #4A90E2, #A34ACB); /* Gradient xanh - tím */
                font-family: Arial, sans-serif;
            }

            .login-container {
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
                text-align: center;
                width: 350px;
            }

            .user-image img {
                width: 80px;
                height: 80px;
                border-radius: 50%;
                margin-bottom: 10px;
            }

            h2 {
                margin: 10px 0;
                font-size: 22px;
                font-weight: bold;
                color: #333;
            }
            input[type="text"],
            input[type="password"] {
                width: 90%;
                padding: 10px;
                margin: 8px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
            }
            .submit {
                width: 100%;
                background: #28a745; /* Xanh lá */
                color: white;
                padding: 12px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                margin-top: 10px;
                transition: 0.3s;
            }

            .submit:hover {
                background: #218838;
            }

        </style>-->
    </head>

    <body>
        <div class="login-container">
            <div class="user-image">
                <img src ="assets/images/images.png"> 
            </div> 
            <h2>User Login</h2>
            <%
                String message = request.getAttribute("message") + "";
                message = message.equals("null") ? "" : message;
            %>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="login" />
                <input type="text" name="txtUserID" placeholder="User ID" required /><br/>
                <input type="password" name="txtPassword" placeholder="Password" required /> <br/>
                <h1 style="color :red;font-size: 15px;"><%=message %><h1/> 
                    <input class="submit" type="submit" value="Login" />
            </form>
        </div>
    </body>
</html>
