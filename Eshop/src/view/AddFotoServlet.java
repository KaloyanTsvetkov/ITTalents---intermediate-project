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
import dao.FotoQueries;

/**
 * Servlet implementation class AddFotoServlet
 */
@WebServlet("/AddFotoServlet")
public class AddFotoServlet extends HttpServlet {
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
			String pID = request.getParameter("fotoProduct").trim();
			String image = request.getParameter("picsProduct").trim();

			response.getWriter().println(pID + " " + image);

			if (ProductIDValidator.validateProductID(pID) && NameValidator.validateProductName(image)) {

				int productID = Integer.parseInt(pID);
				image = "pics/" + image;

				FotoQueries fotoQueries = new FotoQueries();
				int fotoId = fotoQueries.addNewFotro(image);

				fotoQueries.addFotoToProduct(productID, fotoId);

				request.setAttribute("addFotoMessage", "Sucessfuly addet a new Foto to product " + productID + ".");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/adminLogIn.jsp");
				rd.forward(request, response);

			} else {
				StringBuilder sb = new StringBuilder();

				if (!ProductIDValidator.validateProductID(pID)) {
					sb.append("Invalid Product ID! ").append(pID).append("<BR><BR>");
				}

				if (!NameValidator.validateProductName(image)) {
					sb.append("Invalid image: Must have min 1 image per product!").append("<BR><BR>");
				}

				request.setAttribute("addFotoMessage", sb.toString());
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/adminLogIn.jsp");
				rd.forward(request, response);
			}
		} else {
			response.sendRedirect("/Eshop/MainServlet");
		}
	}

}
