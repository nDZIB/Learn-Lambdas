<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Overview of catalog</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>Explicit Category view</h1>

		<br>
		<div class="container">
			<nav class="navbar navbar-right">
				<ul class="nav navbar-nav">
					<li><a href="/view-exp-catalog.pcat">Product Catalog</a></li>
					<li><a href="/modify-category.pcat">Add new Category</a></li>
				</ul>
			</nav>
		</div>
		<div class="container">
			<sc:forEach items="${category}" var="cat">
			<div class="col-md-3">
			<div class ="a-block">
				<hr>
				<h1><b>${cat.categoryName}</b><br></h1> 
				<b>${cat.categoryDescription}</b><br><br>
				</div>
				<a class="btn btn-success"
					href="/view-expcategory-product.pcat?categoryName=${cat.categoryName}&categoryDescription=${cat.categoryDescription}">Explore</a>
					&nbsp;&nbsp;
					<a class="btn btn-danger"
					href="/modify-category.pcat?id=1&categoryName=${cat.categoryName}&categoryDescription=${cat.categoryDescription}">
						Modify</a>
						<a class="btn btn-danger"
					href="/modify-category.pcat?id=0&categoryName=${cat.categoryName}&categoryDescription=${cat.categoryDescription}">
						Delete</a>
						</div>
			</sc:forEach>
		</div>
		<br>
		<br>
		<a class = "link" href="/user-signup.pcat">Sign Up for account</a>
		<form action="/login.pcat" method="POST" class="sign-in-form">
			<fieldset>
				<legend>Switch User</legend>
				<label>User Name:&nbsp;</label><input type="text" name="userName"><br>
				<label>Password:&nbsp;</label><input type="password"
					name="userPassword"><br> <input  class = "btn btn-success" type="submit"
					value="Login">
			</fieldset>
		</form>
	</div>
	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>