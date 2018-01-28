<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/basicstyle.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Customer Header Page</title>
</head>
<body>
	<nav>
			<form action="CustomerServlet" class="nav-form" id="nav-form">
				<input type="hidden" id="user-request" name="user-request"/>
				<a href="customer.jsp">HOME</a>
				<input type="submit" id="1" name="customer-functionality" value="View All Bikes"/>
				<input type="submit" id="3" name="customer-functionality" value="Return the Bike"/>
				<input type="submit" id="4" name="customer-functionality" value="View Rent History"/>
				<span id="user-name-container">
					<i class="fa fa-user fa-lg" aria-hidden="true"></i> Welcome, <span class="user-name">${sessionScope.userName }</span>		
				</span>
				<span id="menu-icon"><i class="fa fa-bars" aria-hidden="true"></i></span>
			</form>
	</nav>
	<div id="logout-container" class="display-none">
		<form action="LoginServlet">
			<input type="submit" value="Logout" id="logout" name="submit-button"/>
		</form>
	</div>
	
	<script src="javascript/basicscript.js"></script>
</body>
</html>