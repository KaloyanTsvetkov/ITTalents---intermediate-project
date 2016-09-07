package view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controler.validator.ProductIDValidator;
import dao.CategoryQueries;
import dao.ProductQueries;
import model.entity.Category;
import model.entity.Product;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
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
		HttpSession session = request.getSession(false);// don't create if it
														// doesn't exist
		if (session != null && !session.isNew()) {
			String productId = request.getParameter("updateProduct");
			request.setCharacterEncoding("UTF-8");

			if (ProductIDValidator.validateProductID(productId)) {

				int id = Integer.parseInt(productId);
				ProductQueries prodQier = new ProductQueries();
				Product prod = prodQier.getProductByID(id);

				if (prod != null) {

					request.setAttribute("product", prod);

					List<Category> caterories = new CategoryQueries().showAllCategories();
					request.setAttribute("caterories", caterories);

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/updateProduct.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("updateMessage", "Don`t have Product with this ID: " + id);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/adminLogIn.jsp");
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("updateMessage", "Invalid Product ID! " + productId);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/adminLogIn.jsp");
				rd.forward(request, response);
			}
		}else {
			response.sendRedirect("/Eshop/MainServlet");
		}

	}

}
