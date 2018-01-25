<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.zilker.rssfeed.bean.RssFeedBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="display.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Display Page</title>
</head>
<body>
	<nav>	
		<form action="RssFeedServlet" method="get">
			<div>
				<i class="fa fa-newspaper-o fa-2x" aria-hidden="true"></i><input type="submit" value="Top Stories" name="topic"/>
			</div>
			<div>
				<i class="fa fa-flag fa-2x" aria-hidden="true"></i><input type="submit" value="India" name="topic"/>
			</div>
			<div>
				<i class="fa fa-globe fa-2x" aria-hidden="true"></i><input type="submit" value="World" name="topic"/>
			</div>
			<div>
				<i class="fa fa-briefcase fa-2x" aria-hidden="true"></i><input type="submit" value="Business" name="topic"/>
			</div>
			<div>
				<i class="fa fa-server fa-2x" aria-hidden="true"></i><input type="submit" value="Technology" name="topic"/>
			</div>
			<div>
				<i class="fa fa-film fa-2x" aria-hidden="true"></i><input type="submit" value="Entertainment" name="topic"/>
			</div>
			<div>
				<i class="fa fa-gamepad fa-2x" aria-hidden="true"></i><input type="submit" value="Sports" name="topic"/>
			</div>
			<div>
				<i class="fa fa-flask fa-2x" aria-hidden="true"></i><input type="submit" value="Science" name="topic"/>
			</div>
		</form>
	</nav>
	<section>
		<header>
			<h1>${topic }</h1>
		</header>
		<%
			JSONObject jsonObject = null;
			JSONArray jsonArray =(JSONArray) request.getAttribute("jsonArray");
			for(int i=0; i < jsonArray.length(); i++) {
					jsonObject = (JSONObject) jsonArray.get(i);
		%>
		<div class="news-container">
		<p><%=jsonObject.getString("description") %></p>
		</div>
		<%
			}
		%>
	</section>
</body>
</html>