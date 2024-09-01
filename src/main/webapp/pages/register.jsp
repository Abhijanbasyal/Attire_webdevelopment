<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register your account now</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
</head>
<body>
	<div class="wrapper">
		<div class="registration_form">
			<div class="title">Registration Form</div>

			<form action="${pageContext.request.contextPath}/UserRegister"
				method="post" enctype="multipart/form-data">
				<div class="form_wrap">
					<div class="input_grp">
						<div class="input_wrap">
							<label for="firstName">First Name</label> <input type="text"
								id="firstName" name="firstName" required>
						</div>
						<div class="input_wrap">
							<label for="lastName">Last Name</label> <input type="text"
								id="lastName" name="lastName" required>
						</div>
					</div>
					<div class="input_wrap">
						<label for="username">Username</label> <input type="text"
							id="username" name="username" required>
					</div>
					<div class="input_wrap">
						<label for="password">Password</label> <input type="password"
							id="password" name="password" required>
					</div>
					<div class="input_wrap">
						<label for="email">Email</label> <input type="text" id="email"
							name="email" required>
					</div>
					<div class="input_wrap">
						<label for="dob">Date of Birth</label> <input type="date" id="dob"
							name="dob" required>
					</div>
					<div class="input_grp">
						<div class="input_wrap">
							<label for="code">Country Code</label>
							<select style="width:120px; height:38.5px; text-align:center" id="code" name="code">
								<option value="+61">Australia (+61)</option>
								<option value="+91">India (+91)</option>
								<option value="+977">Nepal (+977)</option>
								<option value="+44">UK (+44)</option>
								<option value="+1">US (+1)</option>
							</select>
						</div>
						<div class="input_wrap">
							<label for="phone">Phone Number</label> <input type="text"
								id="phone" name="phone" style="width:220px" required>
						</div>
					</div>
					<div class="input_wrap">
						<label for="region">Country/Region</label> <input
							type="text" id="region" name="region" required>
					</div>
					<div class="input_wrap">
						<label for="image">Profile Picture</label> <input type="file"
							id="image" name="image" required>
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