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
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background: linear-gradient(to right, #3b82f6, #d946ef);
                font-family: Arial, sans-serif;
            }

            .login-container {
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                width: 400px;
                text-align: center;
                position: relative;
            }
            .login-container img {
                width: 127px;
                height: 127px;
                border-radius: 50%;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3); 
            }


            h2 {
                margin-bottom: 20px;
                color: #333;
            }

            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 10px;
                margin: 10px -8px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
            }

            .submit {
                width: 100%;
                padding: 10px;
                background-color: #22c55e; /* Màu xanh lá cây */
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                margin-top: 10px;
            }

            h1 {
                color: red;
                font-size: 15px;
            }
        </style>
    </head>

    <body>
        <div class="login-container">
            <div class="user-image">
                <img src ="assets/images/images.png"> 
            </div> 
            <h2>User Login</h2>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="login" />
                <input type="text" name="txtUserID" placeholder="User ID" required /><br/>
                <input type="password" name="txtPassword" placeholder="Password" required /> <br/>

                <input class="submit" type="submit" value="Login" />
            </form>
        </div>
    </body>
</html>
