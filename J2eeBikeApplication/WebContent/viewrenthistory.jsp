<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="customerheader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/basicstyle.css" />
<title>Insert title here</title>
</head>
<body>
	<div>
		<table id="table">
			<thead>
				<tr>
					<th class="flex-grow-1">Bike Id</th>
					<th>Registration Number</th>
					<th>Rented Date & Time</th>
					<th>Rent Duration</th>
					<th>Estimated Amount</th>
					<th>Advance Paid</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rentBeanList }" var="rentBean">
					<form action="CustomerServlet" method="get">
						<tr>
							<td class="flex-grow-1">${rentBean.getBikeId() }</td>
							<td>${rentBean.getRegistrationNumber() }</td>
							<td>${rentBean.getRentedDateTime() }</td>
							<td>${rentBean.getDuration() }</td>
							<td>${rentBean.getEstimatedAmount() }</td>
							<td>${rentBean.getAdvancePaid() }</td>
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