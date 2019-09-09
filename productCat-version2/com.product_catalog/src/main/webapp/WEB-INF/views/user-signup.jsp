<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>signup</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>User Sign up</h1>

		<div>
			<form action="/user-signup.pcat" method="POST">
				<fieldset>
					<legend>Sign Up For A User Account</legend>
					<label>Your name:&nbsp;</label><input type="text"
						name="userRealName"><br> <label>User
						Name:&nbsp;</label><input type="text" name="userName"><br> <label>Password:&nbsp;</label><input
						type="password" name="userPassword"><br> <input
						type="submit" value="Sign Up">
				</fieldset>
			</form>
		</div>
	</div>
	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>