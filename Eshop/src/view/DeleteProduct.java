package view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controler.validator.ProductIDValidator;
import dao.ProductQueries;
import model.entity.Product;

/**
 * Servlet implementation class ProductActions
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
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
		HttpSession session = request.getSession(false);// don't create if it doesn't exist
		if (session != null && !session.isNew()) {
			String productId = request.getParameter("deleteProduct");
			if (ProductIDValidator.validateProductID(productId)) {
				int id = Integer.parseInt(productId);
				ProductQueries prodQier = new ProductQueries();
				Product prod = prodQier.getProductByID(id);

				if (prod != null) {
					int result = prodQier.removeProductByID(id);

					StringBuilder sb = new StringBuilder();
					if (result < 0) {
						sb.append("Failure delete!").append("<BR>");
					} else {
						sb.append("Successfully deleted!").append("<BR>");
					}

					request.setAttribute("deleteMessage", sb.toString());
				} else {
					request.setAttribute("deleteMessage", "Don`t have Product with this ID: " + id);
				}
			} else {
				request.setAttribute("deleteMessage", "Invalid Product ID! " + productId);
			}

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/adminLogIn.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("/Eshop/MainServlet");
		}

	}

}
