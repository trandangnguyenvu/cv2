<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
this is cart
</body>
</html>



<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> --%>




<%-- CART.JSP BY JAVA --%>




<%@ page import="java.util.*,model.*,dao.*"%>

<form action="PayController" method="get">

<%
//width='100%' border='1' style='border:solid 1px black;'
out.print("<div style='padding:10px;'><table width='100%' ><tr><th>Products in cart: </th><th>Price</th><th>Quantity</th><th>Amount</th><th ></th></tr>");

//List<Product> l = (List<Product>) session.getAttribute("cartbean");
CartBig cb = (CartBig) session.getAttribute("cartbean"); 
//Cart c = (Cart) request.getAttribute("cartbean"); 24/5-1
//List<Product> l = (List<Product>) c; NOT WORK => KEY : for (Product p:c.getItems())
for (Cart c:cb.getItems()) {
for (Product p:c.getItems()) { // ++++++++++ ---- KEY : for (Product p:c.getItems()) ---- ++++++++++
	out.print("<tr>");
	out.print("<td>" + p.getName() + "</td>");
	out.print("<td>$ " + p.getPrice() + "</td>");
	out.print("<td>" + p.getNumber() + "</td>");
	out.print("<td>$ " + p.getAmount() + "</td>");
	//out.print("<td><a href='#'>Delete</a></td>");//style='border:0px;'
	out.print("</tr>");
}
/*
out.print("<tr><td></td><td></td><td></td><td><b>Total: </b>$ " + c.getAmount() +"</td><td ></td></tr>");
//out.print("<tr><td style='border:0px;'></td><td style='border:0px;'></td><td  style='text-align:right;border:0px;'></td><td style='border:0px;'><b>Total: </b>$ " + c.getAmount() +"</td><td ></td></tr>");
out.print("</table></div><br>");
*/
}
%>


<div style='padding:10px;'>
<table >
	<tr>
		<th>Customer name:</th>		
		<% out.print("<td>" + session.getAttribute("acc") + "</td>"); %>
	</tr>
	
	<tr>
		<th width="165px">Customer address:</th>		
		<% out.print("<td>" + session.getAttribute("address") + "</td>"); %>
	</tr>

	<tr>
		<th>Customer email:</th>		
		<% out.print("<td>" + session.getAttribute("mail") + "</td>"); %>
	</tr>	
</table>
</div>


<input type="hidden" name="action" value="addcarttodb" />
<div style='padding:10px;'><input type="submit" value="submit" /></div>
</form>