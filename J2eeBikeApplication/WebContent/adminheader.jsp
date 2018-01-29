<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/basicstyle.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Admin header page</title>
</head>
<body>
	<nav>
		<div>
			<form action="AdminServlet" class="nav-form" id="nav-form">
				<input type="hidden" id="user-request" name="user-request"/>
				<a href="admin.jsp">HOME</a>
				<input type="submit" name="admin-functionality" value="View All Bikes"/>
				<input type="submit" name="admin-functionality" value="View All Rented Bikes"/>
				<input type="submit" name="admin-functionality" value="Add New Bike"/>
				<input type="submit" name="admin-functionality" value="View Customer Details"/>
				<span id="user-name-container">
					<i class="fa fa-user fa-lg" aria-hidden="true"></i> Welcome, <span class="user-name">${sessionScope.userName }</span>		
				</span>
				<span id="menu-icon"><i class="fa fa-bars" aria-hidden="true"></i></span>
			</form>
		</div>
	</nav>
	<div id="logout-container" class="display-none">
		<form action="LoginServlet">
			<input type="submit" name="submit-button" value="Logout"/>
		</form>
	</div>
	
	<script src="javascript/basicscript.js"></script>	
</body>
</html>