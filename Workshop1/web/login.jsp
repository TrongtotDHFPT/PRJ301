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
                <h1 style="color :red;font-size: 15px;"><%=message %><h1/> 
                    <input class="submit" type="submit" value="Login" />
            </form>
        </div>
    </body>
</html>
