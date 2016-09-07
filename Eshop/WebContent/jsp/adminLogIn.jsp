<%@ page language="java" import="java.util.*"%>
<%@ page import="dao.*"%>
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

input[type=submit] {
	background-color: #9933ff;
	border: none;
	color: white;
	padding: 8px 30px;
	text-decoration: none;
	margin: 4px 2px;
	cursor: pointer;
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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wellcome Admin</title>
</head>
<body>
	<CENTER>
		<img src="pics/Admin.gif" alt="Admin View"
			style="width: 304px; height: 228px;"> <BR>
		<h1>
			Welcome,
			<%
			out.print(request.getSession().getAttribute("userName"));
		%>!
		</h1>
		<a class="two" href="/Eshop/LogOutServlet" method="POST">Logout</a>
	</CENTER>
	<BR>
	<form action="/Eshop/DeleteProduct" method="POST">
		<h2>Delete a Product:</h2>
		Enter a Product Number: <input type="text" NAME="deleteProduct">
		<BR> <input type="submit" value="Delete">
		<h3 style="color: red">
			<%
				if (request.getAttribute("deleteMessage") != null) {
					out.println(request.getAttribute("deleteMessage"));
				}
			%>
		</h3>
	</form>
	<BR>
	<BR> *********************************************
	<form action="/Eshop/UpdateProduct" method="POST">
		<h2>Update a Product:</h2>
		Enter a Product Number: <input type="text" NAME="updateProduct">
		<BR> <input type="submit" value="Update">
		<h3 style="color: red">
			<%
				if (request.getAttribute("updateMessage") != null) {
					out.println(request.getAttribute("updateMessage"));
				}
			%>
		</h3>
	</form>
	<BR>
	<BR> *********************************************
	<form action="/Eshop/AddNewProduct" method="POST">
		<h2>Add a New Product:</h2>
		Name:<br> <input type="text" name="pName" title="Can't be empty"><br>
		<br> Quantity:<br> <input type="text" name="pQuantity"
			title="Must be 0 or greater number"><br> <br>
		<%
			out.println("Categorie:<br><br><select name=\"pCategorie\">");

			List<Category> caterories = new CategoryQueries().showAllCategories();
			for (Category categorie : caterories) {
				out.print("<OPTION value=\"" + categorie.getId() + "\"");
				out.println(">" + categorie.getCategoryName() + "</OPTION>");
			}
			out.println("</select><br><br>");
		%>
		Price:<br> <input type="text" name="pPrice"
			title="Must be 0 or greater"><br> <br> Description:<br>
		<textarea rows="4" cols="30" name="pDescr">Enter description here...</textarea>
		<br><br> 
		Select images: <input type="file" name="img" multiple="multiple" title="Must have min 1 image per product!"><BR>
		<BR> <input type="submit"> <br>
		<h3 style="color: red">
			<%
				if (request.getAttribute("addProductMessage") != null) {
					out.println(request.getAttribute("addProductMessage"));
				}
			%>
		</h3>
	</form>
	<BR>
	<BR> *********************************************
	<form action="/Eshop/AddFotoServlet" method="POST">
		<h2>Add a Foto to Product:</h2>
		Enter a Product Number: <input type="text" NAME="fotoProduct"><br> 
		<br> Select images: <input type="file" name="picsProduct" multiple="multiple" title="Must have min 1 image per product!"><BR>
		<BR> <BR> <input type="submit" value="Add Foto">
		<h3 style="color: red">
			<%
				if (request.getAttribute("addFotoMessage") != null) {
					out.println(request.getAttribute("addFotoMessage"));
				}
			%>
		</h3>
	</form>
</body>
</html>