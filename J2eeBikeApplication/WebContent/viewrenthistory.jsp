<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/table.css" />
<title>View Rent History</title>
</head>
<body>
	<jsp:include page="customerheader.jsp" />
	<div>
		<table id="table">
			<thead>
				<tr>
					<th>Bike Name</th>
					<th>Registration Number</th>
					<th>Rented Date & Time</th>
					<th>Rent Duration</th>
					<th>Actual Charge</th>
					<th>Penalty Duration</th>
					<th>Penalty Charge</th>
					<th>Total Charge</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rentBeanList }" var="rentBean">
					<tr>
						<c:forEach items="${rentCalculatorBeanList }" var="revenueBean">
							<c:choose>
							<c:when test="${rentBean.getTransactionId() eq revenueBean.getTransactionId() }">
								<c:forEach items="${bikeBeanList }" var="bikeBean">
									<c:choose>
									<c:when test="${rentBean.getBikeId() == bikeBean.getBikeId() }">
										<td>${bikeBean.getManufacturer() } - ${bikeBean.getBikeName() }</td>
									</c:when>
									</c:choose>
								</c:forEach>
								<td>${rentBean.getRegistrationNumber() }</td>
								<td>${rentBean.getRentedDateTime() }</td>
								<td>${revenueBean.getHoursRented() } hours</td>
								<td>&#8377; ${revenueBean.getActualCharge() }</td>
								<td>${revenueBean.getPenaltyHours() } hours</td>
								<td>&#8377; ${revenueBean.getPenaltyCharge() }</td>
								<td>&#8377; ${revenueBean.getTotalCharge() }</td>
							</c:when>
							</c:choose>
						</c:forEach>
					</tr>
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