<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>


<%-- JSTL --%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ page import="java.util.*,model.*,dao.*"%>

<form action="PayController" method="get">

<div style="padding:10px;">
<table width="100%" border="1" > <%-- cellpadding="4" -- <td>${product.amount}</td> --%>

	<tr>
		<th>Products in cart: ${sessionScope.cartbean.getNumber()}</th>
		<th>Price</th>
		<th>Quantity</th>
		<th>Amount</th>
		<th></th>
	</tr>
	
	<c:forEach var="product" items="${sessionScope.cartbean.getItems()}">
		<tr>
			<td>${product.name}</td>
			<td>$ ${product.price}</td>
			<td>${product.number}</td>
			<td>$ ${product.amount}</td>
			<td ><a href="AddToCartController?action=delete&image=${product.id}">Delete</a></td>
		</tr>
	</c:forEach>
	
		<tr >
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td ><b>Total:</b> $ ${sessionScope.cartbean.getAmount()}</td>
		</tr>
</table>
</div>

<div style='padding:10px;'><a href="AddMoreController?action=addmore&page=1" ><b>Add more products</b></a></div>

<div style='padding:10px;'><a href="BackController">Back to home</a>
				<a href="home.jsp?page=1">1</a><span> | </span>
				<a href="home.jsp?page=2">2</a><span> | </span>
				<a href="home.jsp?page=3">3</a></div>
<div style='padding:10px;'>
<table >
	<tr>
		<th>Customer name:</th>	
		<td> ${acc}</td>
	</tr>
	
	<tr>
		<th width="165px">Customer address:</th>
		<td> ${address}</td>
	</tr>

	<tr>
		<th>Customer email:</th>
		<td> ${mail}</td>
	</tr>	
</table>
</div>



<%-- <input type="hidden" name="action" value="addcarttodb" /> --%>
<div style='padding:10px;'><input type="submit" value="submit" /></div>
</form>