<%-- 
    Document   : bookForm
    Created on : Mar 2, 2025, 9:44:34 PM
    Author     : trong
--%>

<%@page import="java.sql.Date"%>
<%@page import="dto.StartupProjectDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String message = request.getAttribute("message") + "";
            message = (message.equals("null")) ? "" : message;
            String txt_projectName_Error = request.getAttribute("txt_projectName_Error") + "";
            txt_projectName_Error = (txt_projectName_Error.equals("null")) ? "" : txt_projectName_Error;
            String txt_statusInValid_Error = request.getAttribute("txt_statusInValid_Error") + "";
            txt_statusInValid_Error = (txt_statusInValid_Error.equals("null")) ? "" : txt_statusInValid_Error;
            String txt_dateError = request.getAttribute("txt_dateError") + "";
            txt_dateError = (txt_dateError.equals("null")) ? "" : txt_dateError;
        %>
        <form action="MainController">
            <input type="hidden" name="action" value = "create"/>
            Project Name :<input type ="text" name="txtProjectName"/> 
            <%=txt_projectName_Error%><br/>

            Description  :<input type ="text" name="txtDescription"/> <br/>

            Status       :<input type ="text" name="txtStatus" />
            <%=txt_statusInValid_Error%><br/>

            Launch Date  :<input type ="date" name="txtDate"value =/> 
            <%=txt_dateError%><br/>
            <input type="submit" value="Save"/> <br>
            <%=message%>
        </form>
        <a href="MainController?action=search&seachTerm= ">Come back Project Dashboard</a>

    </body>
</html>
