<%-- 
    Document   : signup
    Created on : Mar 8, 2025, 9:42:21 PM
    Author     : trong
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h3{
                color :red;
                font-size: 15px;
            }
            
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <h1>Sign up</h1>
        <%
            String strName = request.getAttribute("strName") + "";
            strName = (strName.equals("null")) ? "" : strName;
            String strUsername = request.getAttribute("strUsername") + "";
            strUsername = (strUsername.equals("null")) ? "" : strUsername;

            String errorName = request.getAttribute("errorName") + "";
            errorName = (errorName.equals("null")) ? "" : errorName;

            String errorUsername = request.getAttribute("errorUsername") + "";
            errorUsername = (errorUsername.equals("null")) ? "" : errorUsername;

            String errorPassword = request.getAttribute("errorPassword") + "";
            errorPassword = (errorPassword.equals("null")) ? "" : errorPassword;

            String errorConfirmPassword = request.getAttribute("errorConfirmPassword") + "";
            errorConfirmPassword = (errorConfirmPassword.equals("null")) ? "" : errorConfirmPassword;

            String message = request.getAttribute("message") + "";
            message = (message.equals("null")) ? "" : message;
        %>
        <form action="MainControllerSignUp" method="post">
            <input type="hidden" name="action" value="signup" />

            <label for="txtName">Name :</label>
            <input type="text" id="txtName" name="txtName" value="<%=strName%>" placeholder="Your Name" /> <br>
            <h3><%=errorName%></h3>

            <label for="txtUserName">Username:</label>
            <input type="text" id="txtUserName" name="txtUserName" value="<%=strUsername%>" placeholder="User Name" /> <br>
            <h3><%=errorUsername%></h3>

            <label for="txtPassword">Password:</label>
            <input type="password" id="txtPassword" name="txtPassword" placeholder="Password" /> <br>
            <h3><%=errorPassword%></h3>

            <label for="txtConfirmPassword">Confirm Password:</label>
            <input type="password" id="txtConfirmPassword" name="txtConfirmPassword" placeholder="Password confirm" /> <br>
            <h3><%=errorConfirmPassword%></h3>

            <input type="submit" value="Sign Up"/>
            <h3><%=message%></h3>
        </form>
        
        <a href="login.jsp" class="btn">Back to Login Page</a>
    </body>
</html>
