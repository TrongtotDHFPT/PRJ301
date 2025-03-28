<%-- 
    Document   : bookForm
    Created on : Feb 27, 2025, 11:15:33 AM
    Author     : trong
--%>

<%@page import="utils.AuthUtils"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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

            .form-container {
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                width: 350px;
            }

            .form-container h2 {
                text-align: center;
                color: #333;
            }

            .form-group {
                margin-bottom: 15px;
                margin-right: 15px;

            }

            .form-group label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
            }

            .form-group input {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
            }

            .form-group input:focus {
                border-color: #4CAF50;
                outline: none;
            }

            .button-group {
                display: flex;
                justify-content: space-between;

            }

            .button-group input {
                width: 48%;
                padding: 10px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                color: white;
            }

            .save-btn {
                background-color: #4CAF50;
            }

            .save-btn:hover {
                background-color: #45a049;
            }

            .reset-btn {
                background-color: #f44336;
            }

            .reset-btn:hover {
                background-color: #d32f2f;
            }
            .error_message{
                height: 457px;
                width: 348px;
            }
        </style>
    </head>

    <body>
        <%
//            String txtBookID_error = request.getAttribute("txtBookID_error") + "";
//            txtBookID_error = txtBookID_error.equals("null") ? "" : txtBookID_error;
//            String txtQuantity_error = request.getAttribute("txtQuantity_error") + "";
//            txtQuantity_error = txtQuantity_error.equals("null") ? "" : txtQuantity_error;
//            String txtTitle_error = request.getAttribute("txtTitle_error") + "";
//            txtTitle_error = txtTitle_error.equals("null") ? "" : txtTitle_error;
//            String txtPublishYear_error = request.getAttribute("txtPublishYear_error") + "";
//            txtPublishYear_error = txtPublishYear_error.equals("null") ? "" : txtTitle_error;
//            String txtPrice_error = request.getAttribute("txtPrice_error") + "";
//            txtPrice_error = txtPrice_error.equals("null") ? "" : txtTitle_error;
        %>
        <div class="form-container">
            <c:set var="isLoggedIn" value="<%=AuthUtils.isLoggedIn(session)%>"/>
            <c:set var="isAdmin" value="<%=AuthUtils.isAdmin(session)%>"/>

            <c:if test="${isAdmin}">
                <h2>Book Form</h2>
                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="add"/>

                    <div class="form-group">
                        <label for="txtBookID">Book ID:</label>
                        <input type="text" id="txtBookID" name="txtBookID"/>
                        <c:if test="${not empty requestScope.txtBookID_error}">
                            <div class="error_message"><%= request.getAttribute("txtBookID_error")%></div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="txtTitle">Title:</label>
                        <input type="text" id="txtTitle" name="txtTitle"/>
                        <c:if test="${not empty requestScope.txtTitle_error}">
                            <div class="error_message"><%= request.getAttribute("txtTitle_error")%></div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="txtAuthor">Author:</label>
                        <input type="text" id="txtAuthor" name="txtAuthor"/>
                    </div>

                    <div class="form-group">
                        <label for="txtPublishYear">Publish Year:</label>
                        <input type="number" id="txtPublishYear" name="txtPublishYear"/>
                        <c:if test="${not empty requestScope.txtPublishYear_error}">
                            <div class="error_message"><%= request.getAttribute("txtPublishYear_error")%></div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="txtPrice">Price:</label>
                        <input type="number" id="txtPrice" name="txtPrice"/>
                        <c:if test="${not empty requestScope.txtPrice_error}">
                            <div class="error_message"><%= request.getAttribute("txtPrice_error")%></div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="txtQuantity">Quantity:</label>
                        <input type="number" id="txtQuantity" name="txtQuantity"/>
                        <c:if test="${not empty requestScope.txtQuantity_error}">
                            <div class="error_message"><%= request.getAttribute("txtQuantity_error")%></div>
                        </c:if>
                    </div>

                    <div class="button-group">
                        <input type="submit" value="Save" class="save-btn"/>
                        <input type="reset" value="Reset" class="reset-btn"/>
                    </div>
                </form>
            </c:if>

            <c:if test="${not isAdmin}">
                <h2 style="color: red;">403 Error</h2>
                <div>
                    <h1>Access Denied</h1>
                    <p>Please login to access this page</p>
                    <a href="login.jsp">Go to Login page</a>
                </div>
            </c:if>
        </div>

    </body>
</html>
