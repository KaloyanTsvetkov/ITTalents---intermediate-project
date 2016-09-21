package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.validator.ProductIDValidator;
import dao.ProductQueries;
import model.entity.Product;

/**
 * Servlet implementation class ProductById
 */
@WebServlet("/ProductById")
public class ProductById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		String productId = request.getParameter("param1");
		
		if (ProductIDValidator.validateProductID(productId)) {

			int id = Integer.parseInt(productId);
			ProductQueries prodQier = new ProductQueries();
			Product prod = prodQier.getProductByID(id);
			if (prod != null) {
				
				request.setAttribute("product", prod);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/showProduct.jsp");
				dispatcher.forward(request, response);
				
			}else{
				out.write("Don`t have Product with this ID: " + productId);
				out.write("<br/>");
				out.write("<FORM action=\"/Eshop/MainServlet\" method=\"GET\"><CENTER><INPUT type=\"submit\" value=\"  Back  \" /><BR> <BR>");
				out.write("</CENTER></FORM>");
				out.close();
			}
		}else{
			out.write("Don`t have Product with this ID: " + productId);
			out.write("<br/>");
			out.write("<FORM action=\"/Eshop/MainServlet\" method=\"GET\"><CENTER><INPUT type=\"submit\" value=\"  Back  \" /><BR> <BR>");
			out.write("</CENTER></FORM>");
			out.close();
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
