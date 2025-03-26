<%-- 
    Document   : home
    Created on : Mar 15, 2025, 9:32:34 PM
    Author     : trong
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <style>
            /* Tổng thể */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            /* Header */
            header {
                background-color: #222;
                color: white;
                padding: 15px;
                text-align: center;
                font-size: 24px;
            }

            /* Nội dung chính */
            .main-content {
                width: 90%;
                max-width: 1200px;
                margin: auto;
                padding: 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            /* Thanh tìm kiếm */
            form {
                display: flex;
                gap: 10px;
                margin-bottom: 20px;
            }

            input, select, button {
                padding: 8px;
                font-size: 16px;
            }

            /* Bố cục chia hai phần: Last Product & Product List */
            .main-container {
                display: flex;
                width: 100%;
                gap: 20px;
            }

            /* Last Product */
            .last-product {
                flex: 1;
                max-width: 250px;
                background: white;
                padding: 10px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .last-product .product-item {
                text-align: center;
            }

            .last-product img {
                width: 100%;
                height: auto;
                object-fit: cover;
                border-radius: 8px;
            }

            /* Danh sách sản phẩm */
            .product-list {
                flex: 3;
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                gap: 20px;
            }

            .product-item {
                background: white;
                padding: 15px;
                border-radius: 8px;
                text-align: center;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .product-item img {
                width: 100%;
                height: 150px;
                object-fit: cover;
                border-radius: 8px;
            }

            /* Phân trang */
            .pagination {
                margin-top: 20px;
                text-align: center;
            }

            .pagination a, .pagination .current-page {
                display: inline-block;
                padding: 8px 12px;
                margin: 0 5px;
                border-radius: 5px;
                text-decoration: none;
            }

            .pagination a {
                background: #007bff;
                color: white;
            }

            .pagination .current-page {
                background: #333;
                color: white;
            }

        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="main-content">
            <h1>Welcome to Home Page</h1>

            <form action="home" method="get">
                <input type="text" name="searchTerm" placeholder="Search products..." value="${searchTerm}">
                <select name="orderBy">
                    <option value="">Sắp xếp theo giá</option>  
                    <option value="desc" ${orderBy == 'desc' ? 'selected' : ''}>Giảm dần</option>
                    <option value="asc" ${orderBy == 'asc' ? 'selected' : ''}>Tăng dần</option>
                </select>
                <select name="category_id">
                    <option value="">Danh mục sản phẩm</option>   
                    <c:forEach var="cate" items="${cateList}">
                        <option value="${cate.category_id}">
                            ${cate.category_name}
                        </option>
                    </c:forEach>
                </select>
                <button type="submit">Search</button>
            </form>


            <div class="last-product">
                <c:if test="${not empty lastProduct}">
                    <c:forEach var="product" items="${[lastProduct]}">
                        <div class="product-item">
                            <div class="image_product">
                                <img src="assets/img/${product.image}" alt="${product.title}">
                            </div>    
                            <div class="name_product">
                                <h3>${product.title}</h3>
                                By ${product.author}
                            </div>
                            <div>
                                <h5><fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" /> ₫</h5>
                                Product ID: ${product.product_id}
                            </div>
                            <div>
                                <c:if test="${product.stock > 0}">
                                    <p>In stock</p>
                                </c:if>
                                <c:if test="${product.stock <= 0}">
                                    <p>Out of stock</p>
                                </c:if>
                            </div>
                            <a href="detail?product_id=${product.product_id}">View Detail</a>
                        </div>
                    </c:forEach>
                </c:if>
            </div>



            <div class="product-list">
                <c:forEach var="product" items="${productsForPage}">
                    <div class="product-item">
                        <div class="image_product">
                            <img src="assets/img/${product.image}" alt="${product.title}">
                        </div>    
                        <div class="name_product">
                            <h3>${product.title}</h3>
                            By ${product.author}
                        </div>
                        <div>
                            <h5><fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" /> ₫</h5>
                            Product ID: ${product.product_id}
                        </div>
                        <div>
                            <c:if test="${product.stock > 0}">
                                <p>In stock</p>
                            </c:if>
                            <c:if test="${product.stock <= 0}">
                                <p>Out of stock</p>
                            </c:if>
                        </div>
                        <a href="detail?product_id=${product.product_id}">View Detail</a>
                    </div>
                </c:forEach>
            </div>    



            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href ="home?page=${currentPage-1}&searchTerm=${searchTerm}">&laquo;Previous</a>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="page">
                    <c:choose>
                        <c:when test="${page == currentPage}">
                            <span class="current-page">${page}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="home?page=${page}&searchTerm=${searchTerm}">${page}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="home?page=${currentPage+1}&searchTerm=${searchTerm}"> Next &raquo;</a>
                </c:if>
            </div>
        </div>  

        <%@include file="footer.jsp" %>
    </body>

</html>