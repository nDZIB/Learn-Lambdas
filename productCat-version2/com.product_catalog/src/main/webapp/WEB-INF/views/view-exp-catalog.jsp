<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Explicit catalog</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>Explicit catalog</h1>
		<br>
		<div class="container">
			<nav class="navbar navbar-right">
				<ul class="nav navbar-nav">
					<li><a href="/view-exp-categories.pcat">Product Categories</a></li>
					<li><a href="/modify-product.pcat">Add new Product</a></li>
				</ul>
			</nav>
		</div>
		<br>
		<h1>Other's Products</h1>
		<ul class="container">
			<sc:forEach items="${otherProducts}" var="otherproduct">
			<div class="col-md-3">
			
			<!-- This form's elements will be hidden. their only role is to ensure that when modifying 
				a product, the search bar is neat (that is, no data is displayed for the user/everything is at the background)-->
			<form action = "/modify-product.pcat" method = "post">
					<img src="data:image/jpeg;base64,${otherproduct.img}" name="productView"/>
					<hr>
					<h5>
						<small>Name:&nbsp;</small><b>${otherproduct.productName}</b><br>
					</h5>
					<h5>
						<small>Price:</small>&nbsp;<b>${otherproduct.productPrice}&nbsp;<span>FCFA</span></b><br>
					</h5>
					<h5>
						<small>Color:</small>&nbsp;${otherproduct.productColor}&nbsp;&nbsp;<br>
					</h5>
					<!--  DON'T WANT TO DISPLAY THE PRODUCT DESCRIPTION
					<h5>${product.productDescription}<br>
					</h5>
					-->
					<h5>
						<small>Category:</small>&nbsp; ${otherproduct.categoryName} &nbsp;<br>
					</h5>
					<!--  DON'T WANT TO DISPLAY THE CATEGORY DESCRIPTION
					<h5>
						<small>Category Description:</small>&nbsp;<small>${product.categoryDescription}
							&nbsp;&nbsp;</small>
					</h5>
					-->
					
					<!-- THESE HIDDEN FIELDS CONTAIN THE DATA TO BE SENT BASED ON ANY REQUEST BY USER -->
				<input type = "hidden"  name = "productName" value="${otherproduct.productName}">
				<input type = "hidden"  name = "productDescription" value="${otherproduct.productDescription}">
				<input type = "hidden"  name = "productColor" value="${otherproduct.productColor}">
				<input type = "hidden"  name = "productPrice" value="${otherproduct.productPrice}">
				<input type = "hidden"  name = "categoryName" value="${otherproduct.categoryName}">
				<input type = "hidden"  name = "categoryDescription" value="${otherproduct.categoryDescription}">
				<!--  
				<input class = "btn btn-success" type = "submit"  name = "modifyFromView" value="Modify">
				<input class = "btn btn-danger" type = "submit"  name = "deleteFromView" value="Delete">
				-->
				</form>
				<hr>
					<br>
				</div>
			</sc:forEach>
		</ul>
		<h1>My Products</h1>
		<ul class="container">
			<sc:forEach items="${myProducts}" var="myproduct">
			<div class="col-md-3">
			
			<!-- This form's elements will be hidden. their only role is to ensure that when modifying 
				a product, the search bar is neat (that is, no data is displayed for the user/everything is at the background)-->
		<form action = "/modify-product.pcat" method = "post">
					<img src="data:image/jpeg;base64,${myproduct.img}" name="productView"/>
					<hr>
					<h5>
						<small>Name:&nbsp;</small><b>${myproduct.productName}</b><br>
					</h5>
					<h5>
						<small>Price:</small>&nbsp;<b>${myproduct.productPrice}&nbsp;<span>FCFA</span></b><br>
					</h5>
					<h5>
						<small>Color:</small>&nbsp;${myproduct.productColor}&nbsp;&nbsp;<br>
					</h5>
					<!--  DON'T WANT TO DISPLAY THE PRODUCT DESCRIPTION
					<h5>${product.productDescription}<br>
					</h5>
					-->
					<h5>
						<small>Category:</small>&nbsp; ${myproduct.categoryName} &nbsp;<br>
					</h5>
					<!--  DON'T WANT TO DISPLAY THE CATEGORY DESCRIPTION
					<h5>
						<small>Category Description:</small>&nbsp;<small>${product.categoryDescription}
							&nbsp;&nbsp;</small>
					</h5>
					-->
					
					<!-- THESE HIDDEN FIELDS CONTAIN THE DATA TO BE SENT BASED ON ANY REQUEST BY USER -->
				<input type = "hidden"  name = "productName" value="${myproduct.productName}">
				<input type = "hidden"  name = "productDescription" value="${myproduct.productDescription}">
				<input type = "hidden"  name = "productColor" value="${myproduct.productColor}">
				<input type = "hidden"  name = "productPrice" value="${myproduct.productPrice}">
				<input type = "hidden"  name = "categoryName" value="${myproduct.categoryName}">
				<input type = "hidden"  name = "categoryDescription" value="${myproduct.categoryDescription}">
				<input class = "btn btn-success" type = "submit"  name = "modifyFromView" value="Modify">
				<input class = "btn btn-danger" type = "submit"  name = "deleteFromView" value="Delete">
				</form>
				<hr>
					<br>
				</div>
			</sc:forEach>
		</ul>
		<br>
		
		<br> <small>this is not necessary here though, we rather
			need to logout</small>
		<form action="/login.pcat" method="POST">
			<fieldset>
				<legend>Switch User</legend>
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