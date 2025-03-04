<%-- 
    Document   : bookForm
    Created on : Mar 2, 2025, 9:44:34 PM
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
        
        <form action="MainController">
            <input type="hidden" name="action" value = "create"/>
            Project Name :<input type ="text" name="txtProjectName"/><br/>
            Description  :<input type ="text" name="txtDescription"/><br/>
            Status       :<input type ="text" name="txtStatus"/><br/>
            Launch Date  :<input type ="text" name="txtDate"/><br/>
            <input type="submit" value="Save"/>
            <input type="submit" value="Reset"/>
        </form>
    </body>
</html>
