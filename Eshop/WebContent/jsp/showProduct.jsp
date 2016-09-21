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
	background-color: lightblue;
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

h4 {
	text-decoration: underline;
}
</style>
<title>Product information</title>
</head>
<body>
	<FORM action="/Eshop/AddToCartServlet" method="POST">
		<%
			Product element = (Product) request.getAttribute("product");

			out.println("<BR><h4>");
			out.println("Product code: </h4>");
			out.println("<input type=\"text\" name=\"pID\" value=\"" + element.getId()+ "\" readonly><BR>");
			out.println("<h4>Product info: </h4>");
			out.println(element.getProductName() + "<BR>");
			out.println("<h4>Description: </h4>");
			out.println(element.getDescription() + "<BR>");
			out.println("<h4>Price: </h4>");
			out.println(element.getPrice() + "<BR>");

			int quantity = element.getQuantity();
			out.print("<h4>Quantity ");
			if (quantity <= 0) {
				out.println(": </h4>");
				out.println("<h3 style=\"color: red\">" + "Not Available" + "</h3>");
			} else {
				out.print(": </h4>");
				out.print("between (1 and " + quantity + ")<BR>");
				out.println("<input type=\"number\" name=\"quantity\" min=\"1\" max=\"" + quantity + "\"><br>");
			}
			out.println("<BR>");
			out.println("<BR>");

			for (Foto foto : element.getFotos()) {
				out.println("<a target=\"_blank\" href=\"" + foto.getPicturePath() + "\">");
				out.println("<img src=\"" + foto.getPicturePath() + "\" alt=" + foto.getPicturePath()
						+ " width=\"200\" height=\"200\" />");
				out.println("</a>");
			}
			out.println("<BR>");
			request.setAttribute("productT", element);
		%>
		<BR>
		<input type="submit" value="Add to Cart" /><BR>
	</FORM>
	<BR>
	<FORM action="/Eshop/MainServlet" method="GET">
		<INPUT type="submit" value="     Back    " /><BR> <BR>
	</FORM>
</body>
</html>