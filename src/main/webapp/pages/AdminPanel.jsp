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
<meta charset="ISO-8859-1">

<title>AdminPanel</title>
<link rel="stylesheet" type="text/css" href="../css/home.css">
<link rel="stylesheet" type="text/css" href="../css/navbar.css">
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
 
    <div id="centered-div">
      <h1>Admin Panel</h1><br><br>
      <button class="btn" onclick="redirectToPage1()">View User</button>
      <button class="btn" onclick="redirectToPage2()">Add Product</button>
      <button class="btn" onclick="redirectToPage3()">View Product</button>
    </div>
</body>
<script>
function redirectToPage1() {
  window.location.href = "../RetrieveUser?action=listUsers";
}
function redirectToPage2() {
	  window.location.href = "../RetrieveUser?action=addProducts";
	}
function redirectToPage3() {
	  window.location.href = "../productsController";
	}
</script>
</html>

