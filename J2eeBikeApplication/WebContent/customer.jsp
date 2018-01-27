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
	<fmt:bundle basename="MessagesBundle">
	<nav>
		<div>
			<form action="CustomerServlet" class="nav-form" id="nav-form">
				<input type="hidden" id="user-request" name="user-request"/>
				<input type="submit" id="1" name="customer-functionality" value="View All Bikes"/>
				<input type="submit" id="3" name="customer-functionality" value="Return the Bike"/>
				<input type="submit" id="4" name="customer-functionality" value="View Rent History"/>
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
			<h1><fmt:message key="TITLE"/></h1>
			<h2><fmt:message key="QUOTES"/></h2>
			<form action="CustomerServlet" id="">
				<input type="submit" id="2" name="customer-functionality" value="Rent a Bike" class="rent-btn"/>
			</form>
		</div>		
	</section>
	</fmt:bundle>
	
	
	<script src="javascript/basicscript.js"></script>		
</body>
</html>