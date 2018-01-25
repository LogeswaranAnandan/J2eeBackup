<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/basicstyle.css" />
<link rel="stylesheet" type="text/css" href="css/customer.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Insert title here</title>
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

	<section>
		<div class="main-container">
			<h1>Welcome to the WORLD OF BIKES</h1>
			<h2> - "Feel the SOUL of every BIKE..!"</h2>
			<form action="CustomerServlet" class="header-form">
				<input type="submit" name="customer-functionality" value="Rent a Bike" id="rent-btn"/>
			</form>
		</div>
		
		<div>
			<fmt:setLocale value="fr_FR"/>
			<fmt:bundle basename="MessagesBundle">
			<fmt:message key="Welcome"/><br/>
			</fmt:bundle>
		</div>
		
		<div>
			
		</div>
		
	</section>
	
	<script src="javascript/basicscript.js"></script>		
</body>
</html>