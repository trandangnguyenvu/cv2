
<%-- SHOWING 01 PRODUCT WITHOUT USING BEAN (REPLACE BY JSTL) --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>





<%-- forEach with table from dataSource. Table from attribute (a list of Bean) : search.jsp --%>
<%-- get attribute (not a list) from servlet: lession11 : using map in EL --%>
<sql:setDataSource var="ds" dataSource="jdbc/ShoppingDB" />

<sql:query dataSource="${ds}" var="results"
	sql="select * from Products where product_id=?">
	<sql:param>${param.image}</sql:param>
</sql:query>

<c:set scope="page" var="image" value="${results.rows[0]}"></c:set>

<c:set scope="page" var="imagesrc" value="${image.product_img_source}"></c:set>
<c:set scope="page" var="imagename" value="${image.product_name}"></c:set>
<c:set scope="page" var="imageprice" value="${image.product_price}"></c:set>
<c:set scope="page" var="imagedes" value="${image.product_des}"></c:set>
<c:set scope="page" var="imagedes" value="${image.product_des}"></c:set>
<c:set scope="page" var="imageid" value="${image.product_id}"></c:set>


<div style="background-color: rgba(209, 212, 218, 0.971);">
	<h5 style="padding: 20px; padding-left: 40px;">${imagename}</h5>
</div>






<div class="container-fluid mt-3">
	<div class="container-fluid">
		<div class="row">


			<!-- col 1 style="padding: 40px;" -->
			<div class="col-12 col-sm-12 col-md-6 col-lg-6 p-3">
				<img width="100%"  src="${imagesrc}" />
			</div>
						
				
			<!--col 2-->
			<div class="col-12 col-sm-12 col-md-6 col-lg-6 p-3">					
				<div>
					<h2 style="color: rgba(207, 22, 22, 0.933);">$ ${imageprice}</h2>
					<p><b>Product description: </b>${imagedes}</p><br>
					<%-- <a href="AddToCartController?action=cart&image=${imageid}"><b>Add to cart</b></a> --%>
					<form action="AddToCartController" method="get">
						<input type="hidden" name="action" value="cart"/>
						<input type="hidden" name="image" value="${imageid}"/>						
						<input type="submit" value="Add to cart"/>
					</form>
				</div>
			</div>


			<!--col 3-->
			<div class="col-12 col-sm-12 col-md-6 col-lg-6 p-3">
				<p></p>
			</div>
		</div>
	</div>
</div>

