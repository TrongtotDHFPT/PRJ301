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
    </head>
    <body>

        <c:if test="${not empty product}">
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
                    <input type="hidden" name="stock" value="${product.stock}"/>

                    <button type="button" onclick="decrease()">−</button>
                    <input type="number" id="quantity" name="quantity" value="1" min="1">
                    <button type="button" onclick="increase()">+</button>
                    <button type="submit">Add to Cart</button>

                    <c:if test="${not empty message}">
                        <p> message </p>
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
                <p> Những Product có liên quan</p>
                <c:forEach var="p" items="${list_sameCategory}">
                    <c:if test="${product.product_id != p.product_id}" >
                        <div>
                            <div class="image_product">
                                <img src= "assets/img/${p.image}"style="width:150px;height:150px;"/> 
                                ${p.title}
                            </div> 
                            <div>
                                <a href="detail?product_id=${p.product_id}">View Detail</a>
                            </div>
                        </div>
                        <p>============================</p>
                    </c:if>
                </c:forEach>
            </div>


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
    </body>
</html>
