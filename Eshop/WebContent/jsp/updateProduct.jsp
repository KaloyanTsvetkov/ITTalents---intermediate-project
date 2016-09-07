<%@ page language="java" import="java.util.*"%>
<%@page import="dao.*"%>
<%@ page import="model.entity.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
input[type=text], select {
	width: 20%;
	padding: 5px 5px;
	margin: 8px 0;
	border: none;
	border-bottom: 2px solid red;
}

textArea {
	width: 25%;
	padding: 5px 5px;
	margin: 8px 0;
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Product</title>
</head>
<body BGCOLOR="#ffe6ff">
	<CENTER>
		<img src="pics/updateProduct.gif" alt="Update View"
			style="width: 304px; height: 228px;"> <BR>
	</CENTER>
	<form action="/Eshop/ConfirmUpdateProduct" method="POST">
		<%
			Product product = ((Product) request.getAttribute("product"));
			out.println("<br><br>ID:<br><input type=\"text\" name=\"pID\" value=\"" + product.getId()
					+ "\" readonly><br><br>");
			out.println(
					"Name:<br><input type=\"text\" name=\"pName\" value=\"" + product.getProductName() + "\"><br><br>");
			out.println("Quantity:<br><input type=\"text\" name=\"pQuantity\" value=\"" + product.getQuantity()
					+ "\"><br><br>");
			out.println("Categorie:<br><select name=\"pCategorie\">");

			List<Category> caterories = (List<Category>) request.getAttribute("caterories");
			for (Category categorie : caterories) {
				out.print("<OPTION value=\"" + categorie.getId() + "\"");
				out.println(">" + categorie.getCategoryName() + "</OPTION>");
			}
			out.println("</select><br><br>");
			out.println("Price:<br><input type=\"text\" name=\"pPrice\" value=\"" + product.getPrice() + "\"><br><br>");
			out.println("Description:<br><textarea rows=\"4\" cols=\"30\" name=\"pDescr\">" + product.getDescription()
					+ "</textarea><br>");
		%>
		<BR> <input type="submit" value="Submit" /> <input type="reset"
			value="Reset ">
	</form>
</body>
</html>