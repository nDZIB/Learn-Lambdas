<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Overview of catalog</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>Product catalog</h1>

		<br>
		<div class="container">
			<nav class="navbar navbar-right">
				<ul class="nav navbar-nav">
					<li><a href="/view-categories.pcat">Product Categories</a></li>
				</ul>
			</nav>
		</div>
		<ul class="container">
			<sc:forEach items="${products}" var="product">
				<div class="col-md-3">
					<img src="data:image/jpeg;base64,${product.img}" name="productView"/>
					<hr>
					<label>Name:&nbsp;</label><b>${product.productName}</b><br>
					<label>Price:&nbsp;</label><b>${product.productPrice}&nbsp;<span>FCFA</span></b><br>
					<label>Color:&nbsp;</label>${product.productColor}<br>
					<label>Category:&nbsp;</label>${product.categoryName}<br>
					
					<!--  dont want to display the category description -->
						<!-- <small>${product.productDescription}</small><br> -->
						<!--   <label>Category Description&nbsp;</label>
							<small>${product.categoryDescription}&nbsp;&nbsp;</small>
						-->
					<hr>
					<br>
				</div>

			</sc:forEach>
		</ul>
		<a href="/user-signup.pcat">Sign Up for account</a>
		<form action="/login.pcat" method="POST" class="sign-in-form">
			<fieldset>
				<legend>Login</legend>
				<label>User Name:&nbsp;</label><input type="text" name="userName"><br>
				<label>Password:&nbsp;</label><input type="password"
					name="userPassword"><br> <input type="submit"
					value="Login">
			</fieldset>
		</form>
	</div>
	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>