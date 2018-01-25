<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/basicstyle.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Customer Header Page</title>
</head>
<body>
	<nav>
		<div>
			<form action="CustomerServlet" class="nav-form">
				<input type="submit" name="customer-functionality" value="View All Bikes"/>
				<input type="submit" name="customer-functionality" value="View & Rent Available Bikes"/>
				<input type="submit" name="customer-functionality" value="Return the Bike"/>
				<input type="submit" name="customer-functionality" value="View Remaining Duration of Rent"/>
				<span id="user-name-container">
					<i class="fa fa-user fa-lg" aria-hidden="true"></i> Welcome, <span class="user-name">${sessionScope.userName }</span>		
				</span>
			</form>
		</div>
	</nav>
	<div id="logout-container" class="display-none">
		<form action="LogoutServlet">
			<input type="submit" value="Logout"/>
		</form>
	</div>
	
	<script src="javascript/basicscript.js"></script>
</body>
</html>