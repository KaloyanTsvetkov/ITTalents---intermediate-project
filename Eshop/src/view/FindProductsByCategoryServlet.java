package view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryQueries;
import dao.ProductQueries;
import model.entity.Category;
import model.entity.Product;

/**
 * Servlet implementation class FindProductsByCategoryServlet
 */
@WebServlet("/FindProductsByCategoryServlet")
public class FindProductsByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/Eshop/MainServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String category = request.getParameter("pCategorie");

		if (category.equals("showAll")) {
			response.sendRedirect("/Eshop/MainServlet");
		} else {
			request.setCharacterEncoding("UTF-8");
			
			CategoryQueries categoryQuery = new CategoryQueries();
			List<Category> categoriesList = categoryQuery.showAllCategories();
			request.setAttribute("categories", categoriesList);
			
			int categotyID = Integer.parseInt(category);
			ProductQueries productQueries = new ProductQueries();
			List<Product> productsList = productQueries.getProductsByCategory(categotyID);
			request.setAttribute("products", productsList);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/home.jsp");
			dispatcher.forward(request, response);
		}
	}

}
