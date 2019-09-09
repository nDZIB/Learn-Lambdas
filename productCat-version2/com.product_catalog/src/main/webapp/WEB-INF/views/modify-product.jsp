<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Modify-prod</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>Modify products</h1>

		<form action="/modify-product.pcat" method="post" enctype="multipart/form-data">
				<label>Product Name&nbsp;</label><input type="text" value="${product.productName}"
				name="newProductName"><br> <label>Product
				Color: &nbsp;</label><input type="text" value="${product.productColor}"
				name="newProductColor"><br> <label>Product
				Description: &nbsp;</label><input type="text"
				value="${product.productDescription}" name="newProductDescription"><br>
			<label>Product category: &nbsp;</label><input type="text"
				value="${product.categoryName}" name="newCategoryName"><br>
			<label>Category Description: &nbsp;</label><input type="text"
				value="${product.categoryDescription}" name="newCategoryDescription"><br>
			<label>Price:</label><input type="text" value="${product.productPrice}" name= "newProductPrice"><br>
			
			
			<input
				type="file" name="productView" value = "Product Preview" placeholder = "product preview" />

			<input class = "btn btn-success" type="submit" value="Save" name="editProduct">&nbsp; 
			<input class = "btn btn-danger" 
				type="submit" value="Delete" name="deleteProduct"> 
			<input class = "btn btn-success" 
				type="submit" value="Add as new" name="addNewProduct"> 
		</form>
	</div>
	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>