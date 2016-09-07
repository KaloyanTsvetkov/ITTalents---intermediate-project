package view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controler.validator.NameValidator;
import controler.validator.ProductIDValidator;
import dao.ProductQueries;

/**
 * Servlet implementation class AddNewProduct
 */
@WebServlet("/AddNewProduct")
public class AddNewProduct extends HttpServlet {
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
			request.setCharacterEncoding("UTF-8");
			String pName = request.getParameter("pName").trim();
			String pQuantity = request.getParameter("pQuantity").trim();
			String category = request.getParameter("pCategorie");
			String pPrice = request.getParameter("pPrice");
			String pDescr = request.getParameter("pDescr");
			String image = request.getParameter("img").trim();

			// response.getWriter()
			// .println(pName + " " + pQuantity + " " + category + " " + pPrice
			// + "
			// " + pDescr + " " + image);

			if (NameValidator.validateProductName(pName) && ProductIDValidator.validateQuantity(pQuantity)
					&& ProductIDValidator.validatePrice(pPrice) && pDescr != null
					&& NameValidator.validateProductName(image)) {

				int quantity = Integer.parseInt(pQuantity);
				int categoryID = Integer.parseInt(category);
				double price = Double.parseDouble(pPrice);
				image = "pics/" + image;

				ProductQueries prodQueries = new ProductQueries();
				int productID = prodQueries.addNewProduct(pName, quantity, categoryID, price, pDescr, image);

				StringBuilder sb = new StringBuilder();

				if (productID > 0) {
					sb.append("Successfully updated product with id: ").append(productID).append("!");
				} else {
					sb.append("Failure! Please try again.");
				}

				request.setAttribute("addProductMessage", sb.toString());
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/adminLogIn.jsp");
				rd.forward(request, response);

			} else {
				StringBuilder sb = new StringBuilder();

				if (!NameValidator.validateProductName(pName)) {
					sb.append("Invalid product name: Can't be empty!").append("<BR><BR>");
				}

				if (!ProductIDValidator.validateQuantity(pQuantity)) {
					sb.append("Invalid quantyti: Must be 0 or greater number!").append("<BR><BR>");
				}

				if (!ProductIDValidator.validatePrice(pPrice)) {
					sb.append("Invalid price: Must be 0 or greater!").append("<BR><BR>");
				}

				if (pDescr == null) {
					sb.append("Invalid descrpion: Please enter it!").append("<BR><BR>");
				}

				if (!NameValidator.validateProductName(image)) {
					sb.append("Invalid image: Must have min 1 image per product!").append("<BR><BR>");
				}

				request.setAttribute("addProductMessage", sb.toString());
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/adminLogIn.jsp");
				rd.forward(request, response);
			}
		} else {
			response.sendRedirect("/Eshop/MainServlet");
		}
	}

}
