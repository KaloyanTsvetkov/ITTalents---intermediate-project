package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.hashing.GeneratorForSault;
import controler.hashing.HashingAlgorithm;
import controler.validator.BgPostcodeValidator;
import controler.validator.EmailValidator;
import controler.validator.NameValidator;
import controler.validator.PasswordValidator;
import dao.CustomerQueries;

/**
 * Servlet implementation class ConfirmRegister
 */
@WebServlet("/ConfirmRegister")
public class ConfirmRegister extends HttpServlet {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("passw").trim();
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String eMail = request.getParameter("eMail").trim();
		String city = request.getParameter("city");
		String street = request.getParameter("street").trim();
		String postCode = request.getParameter("postCode").trim();

		if (NameValidator.validateName(firstName) && NameValidator.validateName(lastName)
				&& NameValidator.validateUserName(userName) && PasswordValidator.checkPassword(password)
				&& EmailValidator.isEmailValid(eMail) && !city.isEmpty() && city != null
				&& BgPostcodeValidator.validatePostcode(postCode)) {

			String sault = HashingAlgorithm.hashingSault(GeneratorForSault.getRandomSalt());
			String passHash = HashingAlgorithm.hashingPassword(sault, password);

			out.println(sault);

			int cityId = Integer.parseInt(city);
			int posCodeInt = Integer.parseInt(postCode);

			CustomerQueries custQueries = new CustomerQueries();
			try {
				custQueries.addNewCustomer(userName, passHash, sault, firstName, lastName, eMail, cityId, street,
						posCodeInt);
			} catch (SQLException e) {
				 response.sendRedirect("/Eshop/MainServlet");
			}

//			String sessionToken = HashingAlgorithm.getUniqueToken();

			request.getSession().setMaxInactiveInterval(1800);// 30 min.
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/userLogIn.jsp");
			dispatcher.forward(request, response);

		} else {
			StringBuilder sb = new StringBuilder();

			if (!NameValidator.validateUserName(userName)) {
				sb.append("Invalid username: (min 3 symbol, no no spaces, max 15 symbols)!").append("<BR><BR>");
			}

			if (!PasswordValidator.checkPassword(password)) {
				sb.append(
						"Invalid password: Must contain at least one number and one uppercase and lowercase letter, and at least 6 or more characters!")
						.append("<BR><BR>");
			}

			if (!NameValidator.validateName(firstName) || !NameValidator.validateName(lastName)) {
				sb.append("Invalid names: Must start with Capital letter  and at least 1 or more characters!")
						.append("<BR><BR>");
			}

			if (!EmailValidator.isEmailValid(eMail)) {
				sb.append("Invalid e-mail: for example - yourEmail@abv.bg !").append("<BR><BR>");
			}

			if (!BgPostcodeValidator.validatePostcode(postCode)) {
				sb.append("Invalid postal code: Only 4 digits. (ex.1000)!").append("<BR><BR>");
			}

			request.getSession().invalidate();
			request.setAttribute("errorMessage", sb.toString());
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/errorRgistration.jsp");
			rd.forward(request, response);
		}

	}

}
