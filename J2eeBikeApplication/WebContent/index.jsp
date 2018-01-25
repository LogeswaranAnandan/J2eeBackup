<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="css/index.css" />
	<title>Student details</title>
</head>
<body>
	<div class="container">
		<form action="LoginServlet" method="post">
			<div class="login-container">
				<span class="welcome">Welcome</span>
			</div>
			<div class="form-label-container">
				<label for="username">USERNAME or PHONENUMBER</label>
				<input type="text" id="username" name="username" placeholder="Enter your username or phonenumber..." required/><br/>
				<label for="password">PASSWORD</label>
				<input type="password" id="password" name="password" placeholder="Enter your password..." required/><br/>
				<input type="submit" name="submit-button" value="LOGIN"/>
			</div>
			<div class="form-line"></div>
				<a href="signup.jsp" class="create-account">Create an account...</a>
		</form>
	</div>
</body>

</html>