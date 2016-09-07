<%@ page language="java" import="java.util.*"%>
<%@ page import="model.entity.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">
<HTML>
<HEAD>
<style>
input[type=text], input[type=PASSWORD], select {
	width: 20%;
	padding: 5px 5px;
	margin: 8px 0;
	border: none;
	border-bottom: 2px solid red;
}

input[type=submit], input[type=reset] {
	background-color: #9933ff;
	border: none;
	color: white;
	padding: 8px 30px;
	text-decoration: none;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
<TITLE>Registration Form</TITLE>
</HEAD>
<BODY BGCOLOR="#99ddff">
	<H2 ALIGN="CENTER">Registration Form</H2>
		<FORM action="/Eshop/ConfirmRegister" method="POST">
			<CENTER>
				User name:<BR> <INPUT TYPE="TEXT" NAME="userName" title="min lenght = 3, max lenght = 15"> 
				<BR>Password:<BR>
				<INPUT TYPE="password" NAME="passw" title="Must contain at least one number and one uppercase and lowercase letter, and at least 6 or more characters">
				<BR>First Name:<BR>
				<INPUT TYPE="TEXT" NAME="firstName" title="Must start with Capital letter and at least 1 or more characters">
				<BR>Last Name:<BR>
				<INPUT TYPE="TEXT" NAME="lastName" title="Must start with Capital letter  and at least 1 or more characters">
				<BR>E - mail:<BR>
				<INPUT TYPE="TEXT" NAME="eMail">
				<BR>Choose City:<BR> <select name="city">
					<%
						List<City> cities = (List<City>) request.getAttribute("cities");
						for (City city : cities) {
							out.println("<OPTION value=" + city.getId() + ">" + city.getCityName() + "</OPTION>");

						}
					%>
				</select>
				<BR> Street:<BR> <INPUT TYPE="TEXT" NAME="street">
				<BR>Postal code:<BR> <INPUT TYPE="TEXT" NAME="postCode" title="Only 4 digits. (ex.1000)"><BR> 
				<BR> <INPUT type="submit" value="Sign Up" />
				<input type="reset" value=" Reset ">
			</CENTER>
		</FORM>
	<FORM action="/Eshop/MainServlet" method="GET">
		<CENTER>
			<INPUT type="submit" value="  Back  " /><BR> <BR>
		</CENTER>
	</FORM>
</html>


