<%@page import="controller.servlets.RetrieveUser"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="controller.statemanagement.SessionManage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Home Panel</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
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
							<div class="cart-container">
								<form
									action="${pageContext.request.contextPath}/cartController"
									method="post" id="cart" style="display: flex;">
									<input type="hidden" name="action" value="listProducts">
									<button type="submit" form="cart" class="search-button"
										style="border: none; background-color: transparent; padding: 0; margin-left: 10px;">
										<img src="http://localhost:8080/images/images/cart.png" alt=""
											width="30px" height="30px" style="vertical-align: middle;">
									</button>
								</form>

							</div>
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
			<div class="row">
				<div class="col-2">
					<h1>
						Discover your style and <br /> shop with confidence <br /> at
						our online store <br />

					</h1>
					<p>Discover the latest trends and styles in clothing with our
						online store. Shop our collection of high-quality and affordable
						clothes for men, women, and kids. From casual wear to formal
						attire, we have everything you need to upgrade your wardrobe.
						Enjoy a hassle-free shopping experience with easy navigation,
						secure payment options, and fast shipping. Start shopping today
						and look your best every day! We guarentee 100% genius product
						with 2 day return policy and 100% cash back.</p>
					<a href="#" class="btn" style="margin-left: 20px;">Explore Now</a>
				</div>
				<div class="col-2">
					<img src="http://localhost:8080/images/images/asd.png" alt="" />
				</div>

			</div>
		</div>
	</div>
	<!-- Featured products -->
	<div class="small-container">
		<h2 class="title">Featured Products</h2>
		<div class="row">
			<div class="col-4">
				<a href="product_details.html"><img
					src="https://i.ibb.co/47Sk9QL/product-1.jpg" alt="" /></a> <a
					href="product_details.html">
					<h4>Red Printed T-shirt</h4>
				</a>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="far fa-star"></i>
				</div>
				<p>NPR 500.00</p>
			</div>

			<div class="col-4">
				<img src="https://i.ibb.co/b7ZVzYr/product-2.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star"></i> <i class="far fa-star"></i> <i
						class="far fa-star"></i>
				</div>
				<p>NPR 500.00</p>
			</div>

			<div class="col-4">
				<img src="https://i.ibb.co/KsMVr26/product-3.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star-half-alt"></i>
				</div>
				<p>NPR 500.00</p>
			</div>

			<div class="col-4">
				<img src="https://i.ibb.co/0cMfpcr/product-4.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="far fa-star"></i> <i class="far fa-star"></i> <i
						class="far fa-star"></i>
				</div>
				<p>NPR 500.00</p>
			</div>
		</div>
		<h2 class="title">Latest Products</h2>
		<div class="row">
			<div class="col-4">
				<img src="https://i.ibb.co/bQ5t8bR/product-5.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="far fa-star"></i>
				</div>
				<p>NPR 500.00</p>
			</div>

			<div class="col-4">
				<img src="https://i.ibb.co/vVpTyBD/product-6.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star"></i> <i class="far fa-star"></i> <i
						class="far fa-star"></i>
				</div>
				<p>NPR 500.00</p>
			</div>

			<div class="col-4">
				<img src="https://i.ibb.co/hR5FGwH/product-7.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star-half-alt"></i>
				</div>
				<p>NPR 500.00</p>
			</div>

			<div class="col-4">
				<img src="https://i.ibb.co/QfCgdXZ/product-8.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="far fa-star"></i> <i class="far fa-star"></i> <i
						class="far fa-star"></i>
				</div>
				<p>NPR 500.00</p>
			</div>
		</div>

		<div class="row">
			<div class="col-4">
				<img src="https://i.ibb.co/nw5xZwk/product-9.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="far fa-star"></i>
				</div>
				<p>NPR 500.00</p>
			</div>

			<div class="col-4">
				<img src="https://i.ibb.co/9HCsmjf/product-10.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star"></i> <i class="far fa-star"></i> <i
						class="far fa-star"></i>
				</div>
				<p>NPR 500.00</p>
			</div>

			<div class="col-4">
				<img src="https://i.ibb.co/JQ2MBHR/product-11.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="fas fa-star-half-alt"></i>
				</div>
				<p>NPR 500.00</p>
			</div>

			<div class="col-4">
				<img src="https://i.ibb.co/nRZMs6Y/product-12.jpg" alt="" />
				<h4>Red Printed T-shirt</h4>
				<div class="rating">
					<i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
						class="far fa-star"></i> <i class="far fa-star"></i> <i
						class="far fa-star"></i>
				</div>
				<p>NPR 500.00</p>
			</div>
		</div>
	</div>


	<div class="footer">
		<div class="container">
			<div class="row">


				<div class="footer-col-2">
					<img src="http://localhost:8080/images/images/attire2.png" alt="" />
					<p>Our Purpose Is To Sustainably Make the Pleasure and Benefits
						of attire to the Many.</p>
				</div>


			</div>
			<hr />
			<p class="copyright">Copyright &copy; 2023 - Attire</p>
		</div>
	</div>
</body>
</html>

