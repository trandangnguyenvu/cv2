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
<h2>All your orders you has submited:</h2>
<c:forEach var="order" items="${sessionScope.cartbean.getItems()}" varStatus="row"><%-- for1 --%>

<h3>Order ${row.index + 1}:</h3>
<table width="100%" border="1" > <%-- cellpadding="4" -- <td>${product.amount}</td> --%>

	<tr>
		<th>Products in cart: ${product.number}</th><%-- ${sessionScope.cartbean.getNumber()} --%>
		<th>Price</th>
		<th>Quantity</th>
		<th>Amount</th>		
	</tr>
	
	<c:forEach var="product" items="${order.getItems()}">
		<tr>
			<td>${product.name}</td>
			<td>$ ${product.price}</td>
			<td>${product.number}</td>
			<td>$ ${product.amount}</td>			
		</tr>
	</c:forEach>
</table>

<p style="text-align:right;"><b>Total: $ </b>${order.getAmount()}</p>

</c:forEach><%-- => for1 --%>
</div>

<%
	session.setAttribute("cartbean", null);
%>

<div style='padding:10px;'><a href="BackController">Back</a></div>

</body>
</html>