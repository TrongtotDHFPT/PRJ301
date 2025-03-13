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
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .form-container {
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                width: 350px;
            }

            .form-container h2 {
                text-align: center;
                color: #333;
            }

            .form-group {
                margin-bottom: 15px;
                margin-right: 15px;

            }

            .form-group label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
            }

            .form-group input {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
            }

            .form-group input:focus {
                border-color: #4CAF50;
                outline: none;
            }

            .button-group {
                display: flex;
                justify-content: space-between;

            }

            .button-group input {
                width: 48%;
                padding: 10px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                color: white;
            }

            .save-btn {
                background-color: #4CAF50;
            }

            .save-btn:hover {
                background-color: #45a049;
            }

            .reset-btn {
                background-color: #f44336;
            }

            .reset-btn:hover {
                background-color: #d32f2f;
            }
            .error_message{
                height: 457px;
                width: 348px;
            }
        </style>
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
         
         <script>
             $(document).ready(function() {
                 // Hiển thị tên file khi chọn file
                 $('#imageUpload').change(function() {
                     const file = this.files[0];
                     if (file) {
                         // Kiểm tra xem file có phải là hình ảnh không
                         if (!file.type.match('image.*')) {
                             alert('Please select an image file (JPEG, PNG, GIF, etc.)');
                             this.value = '';
                             $('#fileInfo').text('No file selected');
                             return;
                         }
                         
                         // Hiển thị tên file và kích thước
                         const fileSize = (file.size / 1024).toFixed(2) + ' KB';
                         $('#fileInfo').text(file.name + ' (' + fileSize + ')');
                         
                         // Hiển thị thanh tiến trình và bắt đầu chuyển đổi sang Base64
                         $('#progressContainer').show();
                         
                         // Thiết lập FileReader để đọc file và chuyển đổi sang Base64
                         const reader = new FileReader();
                         
                         reader.onprogress = function(event) {
                             if (event.lengthComputable) {
                                 const percentLoaded = Math.round((event.loaded / event.total) * 100);
                                 $('#progressBar').css('width', percentLoaded + '%');
                             }
                         };
                         
                         reader.onload = function(e) {
                             // Hoàn thành tiến trình
                             $('#progressBar').css('width', '100%');
                             
                             // Lưu trữ dữ liệu Base64 vào input ẩn
                             const base64String = e.target.result;
                             $('#txtImage').val(base64String);
                             
                             // Hiển thị hình ảnh xem trước
                             $('#imagePreview').html('<img src="' + base64String + '" alt="Preview">');
                             
                             // Ẩn thanh tiến trình sau 1 giây
                             setTimeout(function() {
                                 $('#progressContainer').hide();
                                 $('#progressBar').css('width', '0%');
                             }, 1000);
                         };
                         
                         reader.onerror = function() {
                             alert('Error reading the file. Please try again.');
                             $('#progressContainer').hide();
                             $('#progressBar').css('width', '0%');
                             $('#fileInfo').text('No file selected');
                         };
                         
                         // Bắt đầu đọc file và chuyển đổi sang Base64
                         reader.readAsDataURL(file);
                     } else {
                         $('#fileInfo').text('No file selected');
                     }
                 });
                 
                 // Xử lý nút Reset
                 $('#resetBtn').click(function() {
                     $('#imagePreview').empty();
                     $('#fileInfo').text('No file selected');
                     $('#txtImage').val('');
                     $('#progressContainer').hide();
                     $('#progressBar').css('width', '0%');
                 });
                 
                   // Để chọn lại file đã tải lên trước đó (nếu có)
                 const existingImageSrc = $('#imagePreview img').attr('src');
                 if (existingImageSrc) {
                     $('#txtImage').val(existingImageSrc);
                 }
             });
         </script>
    </body>
</html>
