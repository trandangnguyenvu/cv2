<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ page import="java.util.*,model.*,dao.*"%>

<div style="padding:10px;">
<table width="100%" border="1" > <%-- cellpadding="4" -- <td>${product.amount}</td> --%>

	<tr>
		<th>Products in cart: ${sessionScope.cartbean.getNumber()}</th>
		<th>Price</th>
		<th>Quantity</th>
		<th>Amount</th>		
	</tr>
	
	<c:forEach var="product" items="${sessionScope.cartbean.getItems()}">
		<tr>
			<td>${product.name}</td>
			<td>$ ${product.price}</td>
			<td>${product.number}</td>
			<td>$ ${product.amount}</td>			
		</tr>
	</c:forEach>

</table>
</div>

<%
	session.setAttribute("cartbean", null);
%>

<div style='padding:10px;'><a href="BackController">Back</a></div>

</body>
</html>