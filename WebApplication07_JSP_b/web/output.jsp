<%-- 
    Document   : output
    Created on : Feb 10, 2025, 11:06:08 AM
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
        <%
            int n = (int)request.getAttribute("NameRemainValueN");
            %>
            <b style="color: crimson;">Bảng cửu chương <%=n%></b> <br/>
                <%
                for(int j = 1; j <= 10; j++){
                    %>
                    |     <%=n%> * <%=j%> = <%=n*j%>    <br/>
                    <%
                }
        %>
    </body>
</html>
