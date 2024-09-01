<%@page import="controller.servlets.RetrieveUser"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="controller.statemanagement.SessionManage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%!SessionManage mySession = new SessionManage();%>
<%
String user = (String) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to E-commerce</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/products.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/navbar.css" />
<style>
      #centered-div {
        width: 400px;
        height: 500px;
        margin: 0 auto;
        border: 2px solid black;
        margin-top: 250px;
        text-align: center;
        display: flex;
        flex-direction: column;
        justify-content: center;
      }

      #centered-div button {
        margin-bottom: 10px;
      }

      .btn{
      	width: 200px;
      	height: 50px;
      	margin-left: 100px;
      }

      #centered-div {
        width: 400px;
        height: 500px;
        margin: 0 auto;
        border: 2px solid black;
        margin-top: 25px;
        text-align: center;
        display: flex;
        flex-direction: column;
        justify-content: center;
      }

      #centered-div button {
        margin-bottom: 10px;
      }

      .topnav {
	  background-color: #333;
	  overflow: hidden;
}

	.topnav a {
	  float: right;
	  color: #f2f2f2;
	  text-align: right;
	  padding: 14px 16px;
	  text-decoration: none;
	  font-size: 17px;
}

	.topnav a:hover {
	  background-color: #ddd;
	  color: black;
	}
	
	
	.topnav a.active {
	  background-color: #04AA6D;
	  color: white;
}
</style>
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
							src="http://localhost:8080/images/Products/${product.imageUrlFromPart}"
							class="card-img-top" alt="...">
					</div>
					<div class="card-body" style="text-align: center;">
						<br>
						<h3 class="card-title">Name: ${product.productName}</h3>
						<h5 class="card-text">Stock: ${product.stock}</h5>
						<h5 class="card-text">Category: ${product.category}</h5>
						<h5 class="card-text">Rating: ${product.rating}</h5>
						<h4 class="card-text">Price: NPR ${product.price}</h4>
					</div>
					<input type="hidden" name="updateId" value="${product.productName}" />
					<form method="post">
						<input type="hidden" name="updateId" value="${product.productName}" />
						<button type="submit">Update</button>
					</form>
					<form action="${pageContext.request.contextPath}/productsController"
					method="post">
					<input type="hidden" name="action" value="deleteProduct">
						<input type="hidden" name="productID" value="${product.productID}">
						<input type="hidden" name="deleteId" value="${product.productID}" />
						<button type="submit" onclick="return confirm('Do you want to delete?')">Delete</button>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>