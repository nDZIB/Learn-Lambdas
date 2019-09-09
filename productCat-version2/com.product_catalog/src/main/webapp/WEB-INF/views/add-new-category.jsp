<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../common-files/comheader.jspf"%>
<title>Modify-category</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>Modify category</h1>

		<form action="/modify-category.pcat" method="post">
			<label>Category Name &nbsp;</label><input type="text"
				value="${category.categoryName}" name="newCategoryName"><br>
			<label>Description &nbsp;&nbsp;</label><input type="text"
				value="${category.categoryDescription}"
				name="newCategoryDescription"><br>
			<br> <input class="btn btn-success" type="submit" value="Add"
				name="addNewCategory">
		</form>
	</div>
	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>
