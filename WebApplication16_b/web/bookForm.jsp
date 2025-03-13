<%-- 
    Document   : bookForm
    Created on : Feb 27, 2025, 11:15:33 AM
    Author     : trong
--%>

<%@page import="dto.BookDTO"%>
<%@page import="utils.AuthUtils"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
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
                <%                   
                    BookDTO book = new BookDTO();
                    if(request.getAttribute("book") == null){
                        
                        request.setAttribute("book", book);
                    }else{
                        book = (BookDTO)request.getAttribute("book");
                    }
                        
                    String action = request.getAttribute("action")+"";
                    if(!action.equals("update")){
                        action = "add";
                    }
                %>

                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="${action}"/>

                    <div class="form-group">
                        <label for="txtBookID">Book ID:</label>
                        <input type="text" id="txtBookID" name="txtBookID" value="${book.bookID}"/>
                        <c:if test="${not empty requestScope.txtBookID_error}">
                            <div class="error_message"><%= request.getAttribute("txtBookID_error")%></div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="txtTitle">Title:</label>
                        <input type="text" id="txtTitle" name="txtTitle" value="${book.title}"/>
                        <c:if test="${not empty requestScope.txtTitle_error}">
                            <div class="error_message"><%= request.getAttribute("txtTitle_error")%></div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="txtAuthor">Author:</label>
                        <input type="text" id="txtAuthor" name="txtAuthor" value="${book.author}"/>
                    </div>

                    <div class="form-group">
                        <label for="txtPublishYear">Publish Year:</label>
                        <input type="number" id="txtPublishYear" name="txtPublishYear" value="${book.publishYear}"/>
                        <c:if test="${not empty requestScope.txtPublishYear_error}">
                            <div class="error_message"><%= request.getAttribute("txtPublishYear_error")%></div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="txtPrice">Price:</label>
                        <input type="number" id="txtPrice" name="txtPrice" value="${book.price}"/>
                        <c:if test="${not empty requestScope.txtPrice_error}">
                            <div class="error_message"><%= request.getAttribute("txtPrice_error")%></div>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="txtQuantity">Quantity:</label>
                        <input type="number" id="txtQuantity" name="txtQuantity" value="${book.quantity}"/>
                        <c:if test="${not empty requestScope.txtQuantity_error}">
                            <div class="error_message"><%= request.getAttribute("txtQuantity_error")%></div>
                        </c:if>
                    </div>

                    <div class="form-group">
                         <label for="txtImage">Book Cover Image:</label>
                         <input type="hidden" id="txtImage" name="txtImage" value="${book.image}"/>
                         <div class="upload-container">
                             <div class="file-upload-wrapper">
                                 <button type="button" class="file-upload-button">Choose an Image</button>
                                 <input type="file" id="imageUpload" class="file-upload-input" accept="image/*"/>
                             </div>
                             <div class="file-info" id="fileInfo">No file selected</div>
                             <div class="progress-bar-container" id="progressContainer">
                                 <div class="progress-bar" id="progressBar"></div>
                             </div>
                         </div>
                         <c:if test="${not empty requestScope.txtImage_error}">
                             <div class="error-message">${requestScope.txtImage_error}</div>
                         </c:if>
                             
                        <div class="image-preview" id="imagePreview">
                             <c:if test="${not empty book.image}">
                                 <img src="${book.image}" alt="${book.title}"/>
                             </c:if>
                         </div>
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
            <script>
             // JavaScript để cải thiện trải nghiệm người dùng
             document.addEventListener('DOMContentLoaded', function () {
                 // Preview image when URL is entered
                 document.getElementById('txtImage').addEventListener('input', function () {
                     const imageUrl = this.value.trim();
                     let previewContainer = document.querySelector('.image-preview');
 
                     if (!previewContainer) {
                         previewContainer = document.createElement('div');
                         previewContainer.className = 'image-preview';
                         this.parentNode.appendChild(previewContainer);
                     }
 
                     if (imageUrl) {
                         // Check if it's a URL or base64 data
                         if (imageUrl.startsWith('data:image') || imageUrl.startsWith('http')) {
                             previewContainer.innerHTML = `<img src="${imageUrl}" alt="Preview" onerror="this.src='assets/images/placeholder.png'; this.alt='Image not available';">`;
                         } else {
                             previewContainer.innerHTML = '<p>Enter a valid image URL or base64 data</p>';
                         }
                     } else {
                         previewContainer.innerHTML = '';
                     }
                 });
             });
         </script>
         
         <!-- Thêm jQuery từ CDN -->
         <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
         
         
    </body>
</html>
