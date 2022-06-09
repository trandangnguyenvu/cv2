<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>pickaboo</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Dongle&family=Roboto:wght@100&display=swap">

<link rel="stylesheet" type="text/css" href="css.css" />





</head>








<body>
	<div class="header">

		<!-- MENU BAR -->
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">

			<div class="container-fluid" id="div-nav">
				<div>
					<p class="navbar-nav me-auto nav-link"
						style="text-align: center; color: rgb(42, 108, 231);">
						<strong>pickaboo</strong>
					</p>
					<!--outside of div collapse => at small screen : not hidden -->
				</div>

				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
					<span class="navbar-toggler-icon"></span>
				</button>

				<!-- id="mynavbar" -->
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav me-auto">
						<li class="nav-item"><a class="nav-link" href="#">Home</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">Products</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">Samsung</a></li>
								<li><a class="dropdown-item" href="#">Iphone</a></li>
								<li><a class="dropdown-item" href="#">Oppo</a></li>
							</ul></li>

						</li>
						<li class="nav-item"><a class="nav-link" href="#">About
								us</a></li>
					</ul>
					
					

					<ul class="navbar-nav "> <%-- <<<<<<<< cartlink >>>>>>>> --%>
						<!-- 'a' tag covered by ul tag (covered by div collapse) + not "me-auto"class => move to right -->
						<a class="nav-link" href="ShowCartOrder?action=showcart"><%=
						session.getAttribute("cartlink")%></a>
					</ul>

					<ul class="navbar-nav ">						
						<h6 style="margin:0px; padding:0px;" class="nav-link"><%= session.getAttribute("acc") %></h6>
					</ul>

					<ul class="navbar-nav "> <%-- Login --%>
						<!-- 'a' tag covered by ul tag (covered by div collapse) + not "me-auto"class => move to right -->
						<a class="nav-link" href="/PRJ321x_A3/Controller?action=login"><%=
						session.getAttribute("login")%></a>
					</ul>

					<ul class="navbar-nav ">
						<%-- register --%>
						<!-- 'a' tag covered by ul tag (covered by div collapse) + not "me-auto"class => move to right -->
						<a class="nav-link" href="/PRJ321x_A3/Controller?action=register"><%=
						session.getAttribute("register")%></a>
					</ul>

					<ul class="navbar-nav ">
						<a class="nav-link" href="/PRJ321x_A3/Logout"><%=session.getAttribute("logout")%></a>
					</ul>

				</div>
			</div>

		</nav>



		<!--banner-image -->
		<div id="banner"
			Style="height: 150px; background-color: rgb(51, 91, 111);">
			<div id="banner-content">
				<h1 id="banner-heading">
					<b>Welcome to pickaboo</b>
				</h1>
				<p id="banner-small-text"></p>
			</div>
			<div id="search-div"> <%-- Controller SearchController2 --%>
				<form class="d-flex" action="SearchController2" method="get">
					<input type="hidden" name="action" value="dosearch" />
					<input class="form-control me-2" type="text"
						placeholder="What are you looking for?" name="search">
					<button class="btn btn-primary" type="submit">Search</button>
				</form>
			</div>
		</div>

	</div>