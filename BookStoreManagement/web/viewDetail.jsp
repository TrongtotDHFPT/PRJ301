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
                    <div>
                        <div class="image_product">
                            <img src= "assets/img/${p.image}"style="width:150px;height:150px;"/> 
                        </div> 
                        <div>
                            <a href="viewDetail?product_id=${p.product_id}">View Detail</a>
                        </div>
                    </div>
                </c:forEach>
            </div>


        </c:if>
    </body>
</html>
