<%-- 
    Document   : login
    Created on : Mar 10, 2025, 9:35:15 AM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .login-container h2 {
            margin-bottom: 20px;
        }
        .login-container input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .login-container button:hover {
            background-color: #0056b3;
        }
        .login-container .forgot-password {
            display: block;
            margin: 10px 0;
            color: #007bff;
            text-decoration: none;
        }
        .login-container .social-login {
            margin: 20px 0;
        }
        .login-container .social-login button {
            background-color: transparent;
            border: 1px solid #ccc;
            color: #333;
            margin: 5px;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        .login-container .social-login button:hover {
            background-color: #f4f4f4;
        }
        .login-container .sign-up {
            margin-top: 20px;
        }
        .login-container .sign-up a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <input type="text" placeholder="Type your username">
        <input type="password" placeholder="Type your password">
        <a href="#" class="forgot-password">Forgot password?</a>
        <button>LOGIN</button>
        <div class="social-login">
            <button>f</button>
            <button>g</button>
            <button>G</button>
        </div>
        <div class="sign-up">
            <span>Or Sign Up Using</span>
            <a href="#">SIGN UP</a>
        </div>
    </div>
</body>
</html>
