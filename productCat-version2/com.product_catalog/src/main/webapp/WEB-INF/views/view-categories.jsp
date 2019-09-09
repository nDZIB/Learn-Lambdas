<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Overview of catalog</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>Category Overview</h1>

		<br>
		<nav class="navbar navbar-right">
			<ul class="nav navbar-nav">
			<li><a href="/view-catalog.pcat">Product Catalog</a>
		</ul>
		</nav>
		<br>
		<br>
		<ul class="container table">
			<sc:forEach items="${category}" var="cat">
			<div class="col-md-3">
			<div class ="a-block">
				<hr>
				<h1><b>${cat.categoryName}</b><br></h1> 
				<b>${cat.categoryDescription}</b><br><br>
				</div>
				<a class="btn btn-success"
					href="/view-category-product.pcat?categoryName=${cat.categoryName}&categoryDescription=${cat.categoryDescription}">Explore</a>
						<hr>
						</div>
			
			
				<!--  <hr>
				<li>Category Name:${cat.categoryName}<br> Category
					Description: ${cat.categoryDescription}<br> 
					<a href="/view-category-product.pcat?categoryName=${cat.categoryName}&categoryDescription=${cat.categoryDescription}">Explore</a>
				</li> -->
				
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