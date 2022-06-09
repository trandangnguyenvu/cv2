<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> --%>
		<!--  SEARCH -->
<%-- </body>
</html> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%-- SHOWING PRODUCTS THAT WAS A LIST RESULTS OF SEARCHING --%>

<div class="container-fluid mt-3">
	<div class="container-fluid">
		<div class="row"> <%-- row --%>					

			<%@ page import="java.util.*,model.*,dao.*"%>			
			
			<h5>${note}</h5>
			
			<c:forEach var="product" items="${searchrslt}">
				<%-- forEach with table (list) from attribute (list of Bean) --%>
				<%-- => not ".rows" --%>
				<%-- get attribute (not a list) from servlet: lession11 : using map in EL --%>
				<%-- forEach with table from datasource : body-image.jsp --%>			
								
				<div class='col-12 col-sm-12 col-md-4 col-lg-4 p-3'>
					<a href="Controller?action=image&image=${product.id}">
					<img  style="display:block;width:100%;height:auto;margin-left: auto;margin-right: auto;" src="${product.src}"/>
					</a>
					<div>
						<P>${product.type}</P>
						<P>${product.name}</P>
						<P>${product.price}</P>
					</div>
				</div>

			</c:forEach>
			
			
			
			<%-- %List<Product> list = (List<Product>) session.getAttribute("search");% --%>
			
			
		</div> <%-- row --%>
	</div>
</div>			