<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="customerheader.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/basicstyle.css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>View All Bikes</title>
</head>
<body>
	<div>
		<table id="table">
			<thead>
				<tr>
					<th class="flex-grow-1">Bike Id</th>
					<th>Manufacturer</th>
					<th>Bike Name</th>
					<th>Charge (per hour)</th>
					<th>Availability</th>
					<th>Rent the Bike</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bikeBeanList }" var="bikeBean">
					<form action="CustomerServlet" method="get">
						<tr>
							<td class="flex-grow-1">${bikeBean.getBikeId() }</td>
							<td>${bikeBean.getManufacturer() }</td>
							<td>${bikeBean.getBikeName() }</td>
							<td>${bikeBean.getCharge() }</td>
							<c:choose>
								<c:when test="${bikeBean.getAvailability() > '0' }">
									<td>Available</td>
									<td>
										<input type="hidden" name="bike-id"	value="${bikeBean.getBikeId() }" />
										<input type="hidden" name="rent-duration" value="" id="rent-duration-${bikeBean.getBikeId() }"/>
										<input type="hidden" name="advance-paid" value="" id="advance-paid-${bikeBean.getBikeId() }"/>
										<input type="submit" name="customer-functionality" value="Rent this Bike" data-id="${bikeBean.getBikeId() }"/>
									</td>
								</c:when>
								<c:otherwise>
									<td>Not Available</td>
									<td>
										<input type="submit" value="Rent this Bike" class="disable-btn" disabled/>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</form>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="back-btn">
		<button><a href="customer.jsp">Go to Home Page</a></button>
	</div>
	<script src="javascript/rentbike.js"></script>
</body>
</html>