<%@ page language="java" import="java.util.*"%>
<%@ page import="model.entity.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
input[type=text] {
	width: 10%;
	padding: 5px 5px;
	margin: 8px 0;
	border: none;
	border-bottom: 2px solid red;
}

input[type=PASSWORD] {
	width: 10%;
	padding: 5px 5px;
	margin: 8px 0;
	border: none;
	border-bottom: 2px solid red;
}

input[type=submit] {
	background-color: #9933ff;
	border: none;
	color: white;
	padding: 8px 30px;
	text-decoration: none;
	margin: 4px 2px;
	cursor: pointer;
}

body {
	background-color: lightblue;
}

a:link, a:visited {
	color: #1a1aff;
	text-decoration: underline;
	cursor: auto;
	font-size: 12px;
}

a.two:link {
	color: #ff0000;
}

a.two:visited {
	color: #0000ff;
}

a.two:hover {
	font-size: 150%;
}

h1 {
	color: #ff4da6;
	text-shadow: 1px 1px 2px black, 0 0 25px blue, 0 0 5px darkblue;
	text-align: center;
}

p {
	font-family: verdana;
	font-size: 20px;
}
</style>
<title>Log In Form</title>
</head>
<body>
	<br>
	<div>
		<FORM action="/Eshop/ConfirmLogIn" method="POST">
			<CENTER>
				<h3 style="color: red">
					<%
						if (request.getAttribute("errorMessage") != null) {
							out.println(request.getAttribute("errorMessage"));
						}
					%>
				</h3>
				User name:<BR> <INPUT TYPE="TEXT" NAME="userName"><BR>
				Password:<BR> <INPUT TYPE="PASSWORD" NAME="pass">
				<p>
					<input type="submit" value="log in" /><BR> <a class="two"
						href="/Eshop/RegistrationForm" method="GET">Register</a>
			</CENTER>
		</FORM>
	</div>
</body>
</html>