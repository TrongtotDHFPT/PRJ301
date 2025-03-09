<%-- 
    Document   : header
    Created on : Mar 8, 2025, 4:44:25 PM
    Author     : trong
--%>
<%@page import="utils.AuthUtils"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Header</title>
        <style>
            /* Reset margin và padding để loại bỏ khoảng cách mặc định */
            body, html {
                margin: 0;
                padding: 0;
                width: 100%;
                height: 100%;
                font-family: Arial, sans-serif;
            }

            /* Container chính */
            .page-header img {
                width: 100%;
                height: 350px;
                display: block;
            }

            .header {
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 10px 20px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                background: linear-gradient(to right, #4A90E2, #A34ACB);
            }

            /* Logo */
            .logo {
                height: 50px; /* Điều chỉnh kích thước logo */
            }

            /* Nút */
            button {
                background-color: #555; /* Màu nền nút */
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                color: #fff;
                transition: background-color 0.3s ease;
                margin: 0 5px;
            }

            button:hover {
                background-color: #777; /* Màu nền khi hover */
            }

            /* Thanh tìm kiếm */
            .search-bar {
                flex-grow: 1;
                margin: 0 20px;
            }

            .search-bar input {
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #ccc;
                font-size: 16px;
            }

            /* Liên kết */
            a {
                text-decoration: none;
                color: inherit;
            }
            .search-bar {
                position: relative;
                display: flex;
                align-items: center;
                width: 300px; /* Điều chỉnh chiều rộng theo ý muốn */
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 5px;
                background-color: #fff;
            }

            .search-bar i {
                font-size: 18px;
                color: gray;
                margin-left: 10px;
            }

            .search-bar input {
                border: none;
                outline: none;
                padding: 8px;
                flex: 1;
                font-size: 16px;
            }

        </style>
    </head>
    <body>
        <div class="page-header">
            <img src="assets/img/header_img.jpg">
        </div>
        <div class="header">
            <div class="logo_header">
                <a href="login.jsp"><img src="assets/img/logo_header.png" alt="Logo Shop" class="logo"></a>
            </div>
            <%
                if (AuthUtils.isLoggedIn(session)) {
            %>
            <div class="search-bar">
                <img src="search-icon.svg" alt="Search">
                <input type="text" placeholder="Search products...">
            </div>


            <a href="cart.jsp">
                <button>Giỏ hàng</button>
            </a>

            <a href="login.jsp">
                <button>Logout</button>
            </a>
            <%}%>
        </div>
    </body>
</html>