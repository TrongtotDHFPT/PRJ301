<%-- 
    Document   : home
    Created on : Mar 15, 2025, 9:32:34 PM
    Author     : trong
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            .main-content {
                padding: 20px;
                max-width: 1200px;
                margin: 0 auto;
            }

            h1 {
                text-align: center;
                color: #333;
            }

            form {
                text-align: center;
                margin-bottom: 20px;
            }

            form input[type="text"], form select {
                padding: 10px;
                margin-right: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            form button {
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            form button:hover {
                background-color: #218838;
            }

            .last-product {
                background-color: #fff;
                padding: 20px;
                margin-bottom: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            .product-list {
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                gap: 20px;
            }

            .product-item {
                background-color: #fff;
                padding: 15px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            .product-item img {
                max-width: 100%;
                height: auto;
                border-radius: 4px;
            }

            .product-item h3 {
                font-size: 18px;
                margin: 10px 0;
            }

            .product-item h5 {
                font-size: 16px;
                color: #28a745;
                margin: 10px 0;
            }

            .product-item a {
                display: inline-block;
                margin-top: 10px;
                padding: 10px 15px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 4px;
            }

            .product-item a:hover {
                background-color: #0056b3;
            }

            .pagination {
                text-align: center;
                margin-top: 20px;
            }

            .pagination a, .pagination span {
                margin: 0 5px;
                padding: 10px 15px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 4px;
            }

            .pagination a:hover {
                background-color: #0056b3;
            }

            .pagination .current-page {
                background-color: #28a745;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="main-content">
            <h1>Welcome to Home Page</h1>

            <!-- Search Form -->
            <form action="home" method="get">
                <input type="text" name="searchTerm" placeholder="Search products..." value="${searchTerm}">
                <select name="orderBy">
                    <option >----Order by----</option>
                    <option value="desc" >Decreasing by price</option>
                    <option value="asc">Ascending by price</option>
                </select>
                <button type="submit">Search</button>
            </form>

            <!-- cateList chưa làm -->    

            <!-- New Product -->
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
                                <h5>$${product.price}</h5>
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



            <!-- Product List -->
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
                            <h5>$${product.price}</h5>
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



            <!-- Pagination -->
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