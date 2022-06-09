<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> --%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>





<c:import url="header.jsp">
	<%-- <c:param name="title" value="PICTURE SQUIRREL home"></c:param> --%>
</c:import>




<c:choose>
	<c:when test="${param.action == 'image' }">		
		<c:import url="body-image.jsp"></c:import>
	</c:when>	
	
	<c:when test="${param.action == 'dosearch' }">
		<c:import url="search.jsp"></c:import>
	</c:when>

	<c:when test="${param.action == 'cart' || param.action == 'delete' || param.action == 'showcart' }">		
		<c:import url="cart.jsp"></c:import>
	</c:when>
	
	
	<c:when test="${param.action == 'addmore'}">
		<c:import url="body.jsp"></c:import>
	</c:when>

	
	<c:otherwise>
		<c:import url="body.jsp"></c:import>
	</c:otherwise>
</c:choose> 




<c:import url="footer.jsp"></c:import>