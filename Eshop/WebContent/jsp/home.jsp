<%@ page language="java" import="java.util.*"%>
<%@ page import="model.entity.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">
<HTML>
<HEAD>
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

a.one:link{
    text-decoration: none;
    font-size:110%;
}

a.one:visited{
    text-decoration: none;
    color: black;
}

/* mouse over link */
a.one:hover{
    text-decoration: underline;
    color: hotpink;
}

a.one:active {
    text-decoration: underline;
}

/* selected link */
a.one:active{
    color: blue;
}
</style>

<TITLE>E-Shop Home Page</TITLE>
</HEAD>
<BODY BGCOLOR="#e6fdf5">
<body>
	<br>
	<h1>Welcome to eShop home page!</h1>
	<div>
		<FORM action="/Eshop/ConfirmLogIn" method="POST">
			<CENTER>
				User name:<BR> <INPUT TYPE="TEXT" NAME="userName"><BR>
				Password:<BR> <INPUT TYPE="PASSWORD" NAME="pass">
				<p>
					<input type="submit" value="log in" /><BR> <a class="two"
						href="/Eshop/RegistrationForm" method="GET">Register</a>
				<h3 style="color: red">
					<%
						if (request.getAttribute("errorMessage") != null) {
							out.println(request.getAttribute("errorMessage"));
						}
					%>
				</h3>
			</CENTER>
		</FORM>
	</div>
	<form action="/Eshop/FindProductsByCategoryServlet" method="POST">
		<%
			out.println("<br>Categorie:<br><select name=\"pCategorie\">");

			List<Category> caterories = (List<Category>) request.getAttribute("categories");
			out.print("<OPTION value=\"" + "showAll" + "\"");
			out.println(">" + "Show All" + "</OPTION>");

			for (Category categorie : caterories) {
				out.print("<OPTION value=\"" + categorie.getId() + "\"");
				out.println(">" + categorie.getCategoryName() + "</OPTION>");
			}
			out.println("</select> ");
		%>
		<input type="submit" value=" Find " />
	</form>
	<FORM>
		<CENTER>
			<%
				List<Product> arrayList = (List<Product>) request.getAttribute("products");

				if (arrayList.isEmpty()) {
					out.println("<BR><h2 style=\"color: red\"> No Products in this Category!</h3>");
				} else {
					Iterator<Product> iter = arrayList.iterator();
					while (iter.hasNext()) {
						Product element = (Product) iter.next();

						out.println("<BR>");
						out.println("<a class=\"one\" href=\"/Eshop/ProductById?param1="+element.getId()+"\" >");
						out.println(element.getId() + "<BR>");
						out.println(element.getProductName() + "<BR>");
						out.println(element.getPrice() + "<BR>");
						out.println("</a>");

						for (Foto foto : element.getFotos()) {
							out.println("<a target=\"_blank\" href=\"" + foto.getPicturePath() + "\">");
							out.println("<img src=\"" + foto.getPicturePath() + "\" alt=" + foto.getPicturePath()
									+ " width=\"200\" height=\"200\" />");
							out.println("</a>");
						}
						out.println("<BR>");
						out.println("<BR>");
					}
					out.println("<BR>");
				}
			%>
		</CENTER>
	</FORM>

</body>

</html>