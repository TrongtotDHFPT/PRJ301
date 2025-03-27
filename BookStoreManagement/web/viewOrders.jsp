<%-- 
    Document   : viewOrders
    Created on : Mar 26, 2025, 8:08:24 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="dto.OrderDTO" %>
<%@page import="dao.OrderDAO" %>
<%@page import="dto.UserDTO" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession userSession = request.getSession();
    UserDTO user = (UserDTO) userSession.getAttribute("user");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    OrderDAO orderDAO = new OrderDAO();
    List<OrderDTO> orderList = orderDAO.getOrdersByUser(user.getUser_id());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/viewOrder.css">

    </head>
    <body>
        <%@include file="header.jsp" %>
        <h2>Danh sách đơn hàng của bạn</h2>

        <% if (orderList.isEmpty()) { %>
        <p>Bạn chưa có đơn hàng nào.</p>
        <% } else { %>
        <table border="1">
            <tr>
                <th>Mã đơn hàng</th>
                <th>Tổng tiền</th>
                <th>Trạng thái</th>
            </tr>
            <% for (OrderDTO order : orderList) {%>
            <tr>
                <td><%= order.getOrder_id()%></td>
                <td><%= order.getTotal_price()%> VNĐ</td>
                <td><%= order.getStatus()%></td>
            </tr>
            <% } %>
        </table>
        <% }%>

        <br>
        <a href="home">Quay lại trang chủ</a>
        <%@include file="footer.jsp" %>
    </body>
</html>
