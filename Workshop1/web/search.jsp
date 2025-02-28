<%-- 
    Document   : search
    Created on : Feb 27, 2025, 11:10:13 PM
    Author     : trong
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <%
            if (session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
        %>

        <h1>Project Dashboard</h1>
        <form action="MainController">
            <input type ="hidden" name="action" value="logout"/>
            <input type="submit" value="logout"/>
        </form>
        <br/>
        
        <form action="MainController">
            <input type ="hidden" name="action" value="search"/>
            Seach Project :<input type="text" name="txtsearchTerm"value="#"/>
            <input type="submit" value="search"/>
        </form>
        <br/>
        <%
            
        %>
        <div class="information_User">
            <img src="assets/images/images.png">
            <h3>Name : <%=user.getName()%> </h3> <br/>
            <h3>Role : <%=user.getRole()%> </h3><br/>
            <div/>    
            <table border = 1>
                <thead>
                    <tr>
                        <td>Project ID<td/>
                        <td>Project Name<td/>
                        <td>Description <td/>
                        <td>Status<td/>
                        <td>Estimated Launch<td/>
                        <td>Action<td/>
                    <tr/>
                <thead/>    

                <tbody>
                    <%
                        
                    %>
                    <tr>
                        <th><th/>
                        <th><th/>
                        <th><th/>
                        <th><th/>
                        <th><th/>
                        <th><th/>
                    <tr/>
                </tbody>
                <table/>

                <%}//ngoặc của  if (session.getAttribute("user") != null) {%>

                </body>

                </html>
