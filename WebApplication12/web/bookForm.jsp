<%-- 
    Document   : bookForm
    Created on : Feb 27, 2025, 11:15:33 AM
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
            String txtBookID_error = request.getAttribute("txtBookID_error")+"";
            txtBookID_error = txtBookID_error.equals("null") ? "": txtBookID_error;
            String txtQuantity_error = request.getAttribute("txtQuantity_error")+"";
            txtQuantity_error = txtQuantity_error.equals("null") ? "": txtQuantity_error;
        %>
        <h1>Book Information</h1>
        <form>
            <input type="hidden" name="action" value="add"/>
            Book ID :<input type="text" id="txtBookID" name="txtBookID"/> <br/>  <div class="error_message"><%=txtBookID_error%> <div/>
            Title :<input type="text" id="txtBookID" name="txtTitle"/><br/>
            Author :<input type="text" id="txtBookID" name="txtAuthor"/><br/>
            Publish Year :<input type="number" id="txtBookID" name="txtPublishYear"/><br/>
            Price :<input type="number" id="txtBookID" name="txtPrice"/><br/>
            Quantity :<input type="number" id="txtBookID" name="txtQuantity"/><br/> <div class="error_message"><%=txtQuantity_error%> <div/>
            <input type="submit" value="Save"/>
            <input type="submit" value="Reset"/>
            
        </form>
    </body>
</html>
