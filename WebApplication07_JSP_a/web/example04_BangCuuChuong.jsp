<%-- 
    Document   : example04_BangCuuChuong
    Created on : Feb 10, 2025, 10:31:46 AM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bảng cửu chương</title>
    </head>
    <body>
        <%
            for(int i = 2; i <= 9; i++){
                %>
                <b style="color: crimson">Bảng cửu chương <%=i%></b> <br/>
                <%
                for(int j = 1; j <= 10; j++){
                    %>
                    |     <%=i%> * <%=j%> = <%=i*j%>    <br/>
                    <%
                }
        }
        %>
    </body>
</html>
