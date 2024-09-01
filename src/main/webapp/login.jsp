<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login to your account</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>
	<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	String registerMessage = (String) request.getHeader("registerMessage");
	%>
	<%
	if (registerMessage != null) {
	%>
	<div class="register-message"><%=registerMessage%></div>
	<%
	}
	%>
	<div class="container" id="container">

		<div class="form-container register-container">
			<form action="${pageContext.request.contextPath}/UserRegister"
				method="post" enctype="multipart/form-data">
				<h1>Register Here.</h1>
				<div class="tab">
					<input type="text" id="firstName" name="firstName"
						placeholder="First Name"> <input type="text" id="lastName"
						name="lastName" placeholder="Last Name">
				</div>

				<div class="tab">
					<input type="text" id="username" name="username"
						placeholder="User Name"> <input type="password"
						id="password" name="password" placeholder="Password"> <input
						type="text" id="email" name="email" placeholder="Email">
				</div>

				<div class="tab">
					<input type="date" id="dob" name="dob" placeholder="DOB"> <select
						style="width: 100px; height: 41.3px; text-align: center" id="code"
						name="code">
						<option value="+61">AUS (+61)</option>
						<option value="+91">IND (+91)</option>
						<option value="+977">NEP (+977)</option>
						<option value="+44">UK (+44)</option>
						<option value="+1">US (+1)</option>
					</select> <input type="text" id="phone" name="phone" style="width: 179px"
						placeholder="Phone Number"> <input type="text" id="region"
						name="region" placeholder="Country/Region">
				</div>

				<div class="tab">
					<img id="img" src="http://localhost:8080/images/images/unload.png" height="150"><br />

					<div
						style="text-align: center; padding: 2%; border: thin solid black; width: 250;">
						<label for="image" style="cursor: pointer;"> Select Image
							<br /> <input id="image" type="file" style="display: none;">
							<span id="imageName" style="color: green;"></span>
						</label>
					</div>
				</div>

				<div style="overflow: auto;">
					<div style="float: right;">
						<button type="button" id="prevBtn" onclick="nextPrev(-1)">Back</button>
						<button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
					</div>
				</div>

				<!-- Circles which indicates the steps of the form: -->
				<div style="text-align: center; margin-top: 40px;">
					<span class="step"></span> <span class="step"></span> <span
						class="step"></span> <span class="step"></span>
				</div>
			</form>
		</div>

		<div class="form-container login-container">
			<form action="LoginServlet" method="post" class="form form-login">
				<h1>Login Here.</h1>
				<div class="form-field">
					<label class="user" for="login-username"> </label> <input
						id="login-username" type="text" class="form-input"
						placeholder="Username" name="userName" required />
				</div>
				<div class="form-field">
					<label class="lock" for="login-password"> </label> <input
						id="login-password" type="password" class="form-input"
						placeholder="Password" name="userPwd" required>
				</div>
				<div class="form-field"></div>

				<button>Login</button>
				<span>follow us on</span>
				<div class="social-container">
					<a href="#" class="social"><i class="lni lni-facebook-fill"></i></a>
					<a href="#" class="social"><i class="lni lni-google"></i></a> <a
						href="#" class="social"><i class="lni lni-linkedin-original"></i></a>
				</div>
			</form>
		</div>

		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1 class="title">
						Attire <br> We sell Class
					</h1>
					<p>if You have an account, login here and start your journey</p>
					<button class="ghost" id="login">
						Login <i class="lni lni-arrow-left login"></i>
					</button>
				</div>
				<div class="overlay-panel overlay-right">
					<h1 class="title">
						Start your <br> journey now
					</h1>
					<p>if you don't have an account yet, join us and start your
						journey.</p>
					<button class="ghost" id="register">
						Register <i class="lni lni-arrow-right register"></i>
					</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		const registerButton = document.getElementById("register");
		const loginButton = document.getElementById("login");
		const container = document.getElementById("container");

		registerButton.addEventListener("click", () => {
			container.classList.add("right-panel-active");
		});

		loginButton.addEventListener("click", () => {
			container.classList.remove("right-panel-active");
		});

		var currentTab = 0; // Current tab is set to be the first tab (0)
		showTab(currentTab); // Display the current tab

		function showTab(n) {
			// This function will display the specified tab of the form ...
			var x = document.getElementsByClassName("tab");
			x[n].style.display = "block";
			// ... and fix the Previous/Next buttons:
			if (n == 0) {
				document.getElementById("prevBtn").style.display = "none";
			} else {
				document.getElementById("prevBtn").style.display = "inline";
			}
			if (n == (x.length - 1)) {
				document.getElementById("nextBtn").innerHTML = "Submit";
			} else {
				document.getElementById("nextBtn").innerHTML = "Next";
			}
			// ... and run a function that displays the correct step indicator:
			fixStepIndicator(n)
		}

		function nextPrev(n) {
			// This function will figure out which tab to display
			var x = document.getElementsByClassName("tab");
			// Exit the function if any field in the current tab is invalid:
			if (n == 1 && !validateForm()) return false;
			// Hide the current tab:
			x[currentTab].style.display = "none";
			// Increase or decrease the current tab by 1:
			currentTab = currentTab + n;
			// if you have reached the end of the form... :
			if (currentTab >= x.length) {
				//...the form gets submitted:
				document.getElementById("regForm").submit();
				return false;
			}
			// Otherwise, display the correct tab:
			showTab(currentTab);
		}

		function validateForm() {
			// This function deals with validation of the form fields
			var x, y, i, valid = true;
			x = document.getElementsByClassName("tab");
			y = x[currentTab].getElementsByTagName("input");
			// A loop that checks every input field in the current tab:
			for (i = 0; i < y.length; i++) {
				// If a field is empty...
				if (y[i].value == "") {
					// add an "invalid" class to the field:
					y[i].className += " invalid";
					// and set the current valid status to false:
					valid = false;
				}
			}
			// If the valid status is true, mark the step as finished and valid:
			if (valid) {
				document.getElementsByClassName("step")[currentTab].className += " finish";
			}
			return valid; // return the valid status
		}

		function fixStepIndicator(n) {
			// This function removes the "active" class of all steps...
			var i, x = document.getElementsByClassName("step");
			for (i = 0; i < x.length; i++) {
				x[i].className = x[i].className.replace(" active", "");
			}
			//... and adds the "active" class to the current step:
			x[n].className += " active";
		}


		let img = document.getElementById('img');
		let input = document.getElementById('image');

		input.onchange = (e) => {
			if (input.files[0])
				img.src = URL.createObjectURL(input.files[0]);
		};



		let imageName = document.getElementById("imageName")

		input.addEventListener("change", () => {
			let inputImage = document.querySelector("input[type=file]").files[0];

			imageName.innerText = inputImage.name;
		})
		</script>
</body>
</html>