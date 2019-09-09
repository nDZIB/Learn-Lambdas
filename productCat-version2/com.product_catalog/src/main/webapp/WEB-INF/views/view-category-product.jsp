<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Overview of catalog</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>${category.categoryName}</h1>
		<h3>${category.categoryDescription}</h3>
		
		<br>	
		<ul class = "container"><li><a href="/view-categories.pcat">Product Categories</a></li></ul>
		<ul class = "container">
			<sc:forEach items="${products}" var="product">
			<div class ="col-md-3"><img src="data:image/jpeg;base64,${product.img}" name ="productView"/>
			<hr>
				Name:&nbsp;<b>${product.productName}&nbsp;&nbsp;</b><br>
				Price:&nbsp;<b>${product.productPrice}&nbsp;&nbsp;</b><br>
					Color:&nbsp;${product.productColor}&nbsp;&nbsp;<br>
					<small> ${product.productDescription}</small>
					<hr><br>
				</div>	
				
			</sc:forEach>
		</ul>
			<a href="/user-signup.pcat">Sign Up for account</a>
			<form action="/login.pcat" method="POST" class ="sign-in-form">
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