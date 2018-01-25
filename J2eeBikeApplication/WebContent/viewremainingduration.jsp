<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="customerheader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/basicstyle.css" />
<link rel="stylesheet" type="text/css" href="css/tablestyle.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Insert title here</title>
</head>
<body>
	<div>
		<table id="table">
			<thead>
				<tr>
					<th>Bike Id</th>
					<th>Registration Number</th>
					<th>Manufacturer</th>
					<th>Bike Name</th>
					<th>Charge(per hour)</th>
					<th>Return the Bike</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bikeBeanList }" var="bikeBean">
					<form action="CustomerServlet" method="get">
						<tr>
							<td>${bikeBean.getBikeId() }</td>
							<td>${bikeBean.getRegistrationNumber() }</td>
							<td>${bikeBean.getManufacturer() }</td>
							<td>${bikeBean.getBikeName() }</td>
							<td>${bikeBean.getCharge() }</td>
							<td>
								<input type="hidden" name="bike-id"	value="${bikeBean.getBikeId() }" />
								<input type="hidden" name="registration-number"	value="${bikeBean.getRegistrationNumber() }" />
								<input type="submit" name="customer-functionality" value="View Remaining Duration"/>
							</td>
						</tr>
					</form>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="back-btn">
		<button><a href="customer.jsp">Go to Home Page</a></button>
	</div>
	<script src="javascript/basicscript.js"></script>
</body>
</html>