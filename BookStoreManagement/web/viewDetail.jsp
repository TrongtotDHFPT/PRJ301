<%-- 
    Document   : viewDetail
    Created on : Mar 18, 2025, 11:33:35 PM
    Author     : trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .low {
                margin-top: 30px;
            }

            .related-products-container {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
            }

            .related-product {
                width: 23%;
                text-align: center;
                border: 1px solid #ddd;
                padding: 10px;
                border-radius: 5px;
                background-color: #f9f9f9;
            }

            .related-product img {
                width: 150px;
                height: 150px;
                object-fit: cover;
            }

            .pagination {
                margin-top: 20px;
                text-align: center;
            }

            .pagination a {
                margin: 0 5px;
                text-decoration: none;
                padding: 5px 10px;
                border: 1px solid #ddd;
                border-radius: 3px;
                color: #333;
                background-color: #fff;
            }

            .pagination a:hover {
                background-color: #eee;
            }

            .pagination a.active {
                font-weight: bold;
                color: red;
                background-color: #ddd;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:if test="${not empty user}">
            <div>
                <div class="image_product">
                    <img src= "assets/img/${product.image}"style="width:150px;height:150px;"/> 
                </div>    

                <div class="name_product">
                    <h3>${product.title}</h3>
                    By ${product.author}
                </div>

                <div>
                    <h5>$${product.price} </h5>
                    Product ID :    ${product.product_id} 
                </div>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="AddToCart"/>
                    <input type="hidden" name="product_id" value="${product.product_id}"/>
                    <!--<input type="hidden" name="stock" value="$//{product.stock}"/>-->

                    <button type="button" onclick="decrease()">−</button>
                    <input type="number" id="quantity" name="quantity" value="1" min="1" readonly >
                    <button type="button" onclick="increase()">+</button>
                    <button type="submit">Add to Cart</button>

                    <c:if test="${not empty message}">
                        <p style="color: red;"> ${message} </p>
                    </c:if>
                </form>
                <div>
                    <c:if test="${product.stock > 0}">
                        <p> In stock  </p>
                    </c:if>
                    <c:if test="${product.stock <= 0}">
                        <p> Out stock </p>
                    </c:if>
                </div>
            </div>

            <div class="low">
                DESCRIPTION
                <p>${product.description}</p>
            </div>
            <div class="low">
                <h3>Sản phẩm cùng loại</h3>
                <div class="related-products-container">
                    <c:forEach var="p" items="${list_sameCategory}" varStatus="status">
                        <c:if test="${product.product_id != p.product_id}">
                            <div class="related-product">
                                <div class="image_product">
                                    <img src="assets/img/${p.image}" />
                                </div> 
                                <div>
                                    <p>${p.title}</p>
                                    <a href="detail?product_id=${p.product_id}">View Detail</a>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>

                <c:if test="${totalPages > 1}">
                    <div class="pagination">
                        <c:if test="${currentPage > 1}">
                            <a href="detail?product_id=${product.product_id}&page=${currentPage - 1}">← Previous</a>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <a href="detail?product_id=${product.product_id}&page=${i}"
                               class="${i == currentPage ? 'active' : ''}">
                                ${i}
                            </a>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <a href="detail?product_id=${product.product_id}&page=${currentPage + 1}">Next →</a>
                        </c:if>
                    </div>
                </c:if>
            </div>


        </c:if>
        <c:if test="${empty user}">
            <a href="login.jsp">login đê bro</a>
        </c:if>
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
