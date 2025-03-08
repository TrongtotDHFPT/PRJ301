<%-- 
    Document   : login.jsp
    Created on : Mar 8, 2025, 4:32:16 PM
    Author     : trong
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
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
                UserName <input type="text" name="txtUserName" placeholder="User Name" required /><br/>
                Password <input type="password" name="txtPassword" placeholder="Password" required /> <br/>
                <h1 style="color :red;font-size: 15px;"><%=message%><h1/> 
                    <input class="submit" type="submit" value="Login" />
            </form>
                    
                    
            <form action="signup.jsp" method="post">
                <input type="hidden" name="action" value="signup" />
                <input class="submit" type="submit" value="sign Up"/>
            </form>
        </div>


    </body>

</html>
