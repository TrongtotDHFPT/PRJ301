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
    <head>
        <!-- Các thẻ meta khác -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <title>Home</title>


</head>
<body>
    <%@include file="header.jsp" %>

    <div class="main-content">
        <form action="home" method="get" class="search-form">
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
                            <img src="assets/img/${product.image}" alt="${product.title}" >
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