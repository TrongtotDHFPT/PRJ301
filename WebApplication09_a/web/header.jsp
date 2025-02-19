<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .header {
        background-color: 3px solid #2EC5C0
    }
    .content_logo{
    display: flex;
   justify-content: center;  /* căn giữa ngang */
    align-items: center;  /* căn giữa dọc*/
}
.content_logo p {
    font: 130px
}


.content_menu ul li a{
    text-decoration: none;
    color: white;
    font-size: 30px;
}
.content_menu ul{
    list-style: none;
    display: flex;
    justify-content: space-around;
}
.content_menu{
    background-color: aqua;
    /* height: 40px; */
    border-radius: 20px;
}
.content_menu ul li{
    background-color: orange;
    width: 110px;
    height: 40px;
    text-align: center;
    border-radius: 28px;
}
.content_menu ul input{
       width: 110px;
    height: 25px;
    margin-top: 5px;
    border-radius: 28px; 
}
</style>

<header class="header">
   <div class="content_logo"> <!--content logo-->
        <img src="images/Logosmart.png" alt="Smart happy home">
	</div> <!-- Finish content logo-->


	<div class="content_menu"> <!--content Menu-->
		<div class="list">
		 <ul>
			<li><a href="#">Home</a></li>
			<li><a href="#">About</a></li>
			<li><a href="#">Products</a></li>
			<li><a href="#">Services</a></li>
			<li><a href="#">Contact</a></li>
                        <input type="text" class="search-input" placeholder="Tìm kiếm...">
		  </ul>  
		</div>
            

	</div> <!-- Finish content Menu-->
</header>