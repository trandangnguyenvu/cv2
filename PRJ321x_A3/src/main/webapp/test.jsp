<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.*,model.*,dao.*" %>
<%
List<Product> list= new ListProductDAO().search("11");
for (Product p:list) {
	out.print("NAME: " +p.getName() + "<br>");
}
%>
</body>
</html>