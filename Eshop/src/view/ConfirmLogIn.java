package view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.hashing.HashingAlgorithm;
import dao.UserQueries;
import model.entity.User;

/**
 * Servlet implementation class ConfirmLogIn
 */
@WebServlet("/ConfirmLogIn")
public class ConfirmLogIn extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("pass");

		if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
			request.getSession().invalidate();
			request.setAttribute("errorMessage", "Invalid username or password");
			RequestDispatcher rd = request.getRequestDispatcher("MainServlet");
			rd.forward(request, response);
		} else {

			UserQueries usrQueries = new UserQueries();
			User user = usrQueries.selectUserByName(userName);

			if (user == null) {
				response.sendRedirect("/Eshop/RegistrationForm");
				return;
			}
			password = HashingAlgorithm.hashingPassword(user.getSault(), password);

			if (user.getLoginPassword().equals(password)) {
				if (user.getIsAdmin()) {
					request.getSession().setMaxInactiveInterval(1200); // 20 min for admin.
					request.getSession().setAttribute("userName", userName);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/adminLogIn.jsp");
					dispatcher.forward(request, response);
				} else {
					request.getSession().setMaxInactiveInterval(1800);// 30 min. for user
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/userLogIn.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				request.getSession().invalidate();
				request.setAttribute("errorMessage", "Invalid password");
				RequestDispatcher rd = request.getRequestDispatcher("MainServlet");
				rd.forward(request, response);
			}
		}
	}

}
