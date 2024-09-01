<%@page import="controller.servlets.RetrieveUser"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="controller.statemanagement.SessionManage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%!SessionManage session = new SessionManage();%>
<%
String user = (String) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/products.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/cart.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/navbar.css" />
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="navbar">
				<div class="logo">
					<a href="index.html"><img
						src="http://localhost:8080/images/images/attire.png"
						alt="RedStore" width="125px" /></a>
				</div>
				<nav>
					<ul id="MenuItems">
						<li><a href="index.html">Home</a></li>
						<li><a href="product.html">Products</a></li>
						<li><a href="#">About</a></li>
						<li><a href="#">Contact</a></li>
						<li>
							<div class="search-container">
								<form action="${pageContext.request.contextPath}/search"
									method="get" id="search" style="display: flex;">
									<input type="text" placeholder="Search..." name="search"
										class="search-bar" style="flex: 1;">
									<button type="submit" form="search" class="search-button"
										style="border: none; background-color: transparent; padding: 0; margin-left: 10px;">
										<img src="http://localhost:8080/images/images/search.png"
											alt="" width="30px" height="30px"
											style="vertical-align: middle;">
									</button>
								</form>
							</div>


						</li>
						<li><a href="#" style="margin-right: 10px;"><img
								src="http://localhost:8080/images/images/user.png" alt=""
								width="30px" height="30px" style="vertical-align: middle" /></a></li>
						<li>
							<form action="${pageContext.request.contextPath}/cartController"
								method="get" id="cart" style="display: flex;">
								<button type="submit" form="cart" class="search-button"
									style="border: none; background-color: transparent; padding: 0; margin-left: 10px;">
									<img src="http://localhost:8080/images/images/cart.png" alt=""
										width="30px" height="30px" style="vertical-align: middle" />
								</button>
							</form>
						</li>
					</ul>
				</nav>
				<!-- <a href="cart.html"><img src="cart.png" alt="" width="30px" height="30px" /></a>
        <a href="search.html"><img src="search.png" alt="" width="30px" height="30px" /></a> -->



				<form
					action="<c:if test="${empty user}">${pageContext.request.contextPath}/login.jsp</c:if><c:if test="${not empty user}">${pageContext.request.contextPath}/LogoutServlet</c:if>"
					method="post">
					<input type="submit"
						value="<c:if test="${empty user}">Login</c:if><c:if test="${not empty user}">Logout</c:if>">
				</form>


			</div>
		</div>
	</div>
	<div class="users-info">
		<div class="users">
			<c:set var="allProducts" value="${requestScope.allProducts}" />
			<c:forEach var="product" items="${allProducts}">
				<div class="card">
					<div class="image-container">
						<img
							src="http://localhost:8080/images/Products/${product.imageUrl}"
							class="card-img-top" alt="...">
					</div>
					<div class="card-body" style="text-align: center;">
						<br>
						<h3 class="card-title">Name: ${product.productName}</h3>
						<h5 class="card-text">Rating: ${product.rating}</h5>
						<h4 class="card-text">Price: NPR ${product.price}</h4>
					</div>
					<input type="hidden" name="addToCart"
						value="${product.productName}" />
					<form action="${pageContext.request.contextPath}/cartController"
						method="post">
						<input type="hidden" name="addToCart"
							value="${product.productName}" /> <input type="hidden"
							name="productId" value="${product.productID}" />
						<button type="submit">Remove</button>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>