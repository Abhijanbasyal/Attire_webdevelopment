
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- The rest of the JSP code goes here -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Account Information</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
</head>
<body>
	<div class="wrapper">
		<div class="registration_form">
			<div class="title">Update Account Information</div>

			<form
				action="${pageContext.request.contextPath}/UpdateServlet"
				method="post" enctype="multipart/form-data">
				<div class="form_wrap">
					<div class="input_grp">
						<div class="input_wrap">
							<label for="firstName">First Name</label> <input type="text"
								id="firstName" name="firstName"
								value="<c:out value="${user.firstName}" />" size="24">
						</div>
						<div class="input_wrap">
							<label for="lastName">Last Name</label> <input type="text"
								id="lastName" name="lastName"
								value="<c:out value="${user.lastName}" />" size="24">
						</div>
					</div>
					<div class="input_wrap">
						<label for="username">Username</label> <input type="text"
							id="username" name="username"
							value="<c:out value="${user.username}" />" size="24" readonly>
					</div>
					<div class="input_wrap">
						<label for="password">Password</label> <input type="password"
							id="password" name="password" size="24">
					</div>
					<div class="input_wrap">
						<label for="email">Email</label> <input type="text" id="email"
							name="email" value="<c:out value="${user.email}" />" size="24"
							readonly>
					</div>
					<div class="input_wrap">
						<label for="dob">Date of Birth</label> <input type="date" id="dob"
							name="dob" value="<c:out value="${user.dob}" />" size="24">
					</div>
					<div class="input_grp">
						<div class="input_wrap">
							<label for="code">Country Code</label> <select id="code"
								name="code">
								<option value="+61"
									<c:if test="${user.code == '+61'}">selected</c:if>>Australia
									(+61)</option>
								<option value="+91"
									<c:if test="${user.code == '+91'}">selected</c:if>>India
									(+91)</option>
								<option value="+977"
									<c:if test="${user.code == '+977'}">selected</c:if>>Nepal
									(+977)</option>
								<option value="+44"
									<c:if test="${user.code == '+44'}">selected</c:if>>UK
									(+44)</option>
								<option value="+1"
									<c:if test="${user.code == '+1'}">selected</c:if>>US
									(+1)</option>
							</select>
						</div>
						<div class="input_wrap">
							<label for="phone">Phone Number</label> <input type="text"
								id="phone" name="phone" style="width: 220px"
								value="${user.phone}">
						</div>
					</div>
					<div class="input_wrap">
						<label for="region">Country/Region</label> <input type="text"
							id="region" name="region" value="${user.region}">
					</div>
					<div class="input_wrap">
						<label for="image">Profile Picture</label> <input type="file"
							id="image" name="image" value="${user.imageUrlFromPart}">
					</div>
					<div class="input_wrap">
						<input type="submit" value="Register Now" class="submit_btn">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>