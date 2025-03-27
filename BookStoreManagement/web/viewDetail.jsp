<%-- 
    Document   : viewDetail
    Created on : Mar 18, 2025, 11:33:35 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết sản phẩm</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/viewDetail.css">
    </head>
    <body>
        <%@include file="header.jsp" %>
       
        <div class="view-detail-container">
            <div class="product-detail">
                <a href="home">Home</a>
                <div class="image_product">
                    <img src="assets/img/${product.image}" alt="${product.title}">
                </div>

                <div class="product-info">
                    <h2>${product.title}</h2>
                    <p><strong>By</strong> ${product.author}</p>
                    <p class="product-price">
                        <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" /> ₫
                    </p>
                    <p><strong>Product ID:</strong> ${product.product_id}</p>
                    <p class="in-stock">In stock: <span>${product.stock}</span></p>

                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="AddToCart"/>
                        <input type="hidden" name="product_id" value="${product.product_id}"/>

                        <div class="quantity-container">
                            <button type="button" onclick="decrease()">−</button>
                            <input type="number" id="quantity" name="quantity" value="1" min="1" readonly>
                            <button type="button" onclick="increase()">+</button>
                        </div>

                        <button type="submit" class="add-to-cart">Add to Cart</button>
                        <c:if test="${not empty message}">
                            <p style="color: red;"> ${message} </p>
                        </c:if>
                    </form>
                </div>
            </div>

            <div class="description-container">
                <h3>Mô tả</h3>
                <p>${product.description}</p>
            </div>

            <div class="related-products-section">
                <h3>Sản phẩm cùng loại</h3>
                <div class="related-products-container">
                    <c:forEach var="p" items="${list_sameCategory}" varStatus="status">
                        <c:if test="${product.product_id != p.product_id}">
                            <div class="related-product">
                                <div class="image_product">
                                    <img src="assets/img/${p.image}" alt="${p.title}">
                                </div>
                                <p>${p.title}</p>
                                <a href="detail?product_id=${p.product_id}">View Detail</a>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- Script tăng giảm số lượng -->
        <script>
            function increase() {
                let quantityInput = document.getElementById("quantity");
                quantityInput.value = parseInt(quantityInput.value) + 1;
            }

            function decrease() {
                let quantityInput = document.getElementById("quantity");
                if (quantityInput.value > 1) {
                    quantityInput.value = parseInt(quantityInput.value) - 1;
                }
            }
        </script>

        <%@include file="footer.jsp" %>
    </body>

</html>
