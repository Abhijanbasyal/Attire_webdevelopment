<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update your account</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
</head>
<body>
	<div class="wrapper">
		<div class="registration_form">
			<div class="title">Update Account Information</div>

			<form action="${pageContext.request.contextPath}/UserUpdate"
				method="post" enctype="multipart/form-data">
				<div class="form_wrap">
					<div class="input_grp">
						<div class="input_wrap">
							<label for="firstName">First Name</label> <input type="text"
								id="firstName" name="firstName" value="${user.firstName}">
						</div>
						<div class="input_wrap">
							<label for="lastName">Last Name</label> <input type="text"
								id="lastName" name="lastName" value="${user.lastName}">
						</div>
					</div>
					<div class="input_wrap">
						<label for="username">Username</label> <input type="text"
							id="username" name="username" value="${user.username}" readonly>
					</div>
					<div class="input_wrap">
						<label for="password">Password</label> <input type="password"
							id="password" name="password" value="${user.password}">
					</div>
					<div class="input_wrap">
						<label for="email">Email</label> <input type="text" id="email"
							name="email" value="${user.email}">
					</div>
					<div class="input_wrap">
						<label for="dob">Date of Birth</label> <input type="date" id="dob"
							name="dob" value="${user.dob}">
					</div>
					<div class="input_grp">
						<div class="input_wrap">
							<label for="code">Country Code</label> <select
								style="width: 120px; height: 38.5px; text-align: center"
								id="code" name="code">
								<option value="+61"
									${user.countryCode == "+61" ? 'selected' : ''}>Australia
									(+61)</option>
								<option value="+91"
									${user.countryCode == "+91" ? 'selected' : ''}>India
									(+91)</option>
								<option value="+977"
									${user.countryCode == "+977" ? 'selected' : ''}>Nepal
									(+977)</option>
								<option value="+44"
									${user.countryCode == "+44" ? 'selected' : ''}>UK
									(+44)</option>
								<option value="+1" ${user.countryCode == "+1" ? 'selected' : ''}>US
									(+1)</option>
							</select>
						</div>
					</div>
					<div class="input_wrap">
						<label for="image">Profile Picture</label> <input type="file"
							id="image" name="image">
					</div>
					<div class="input_wrap">
						<input type="submit" value="Update Now" class="submit_btn">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
