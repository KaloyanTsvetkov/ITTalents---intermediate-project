package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/Eshop/MainServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);// don't create if it doesn't exist
		
		String username = (String) request.getSession().getAttribute("userName");
		if (username != null && !session.isNew()) {
			
			String product = request.getParameter("pID");
			String quantity = request.getParameter("quantity").trim();
			

			out.println(product);
			out.println(quantity);
			
		}else{
			request.setAttribute("errorMessage", "You must be Log in!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/userMustLogIn.jsp");
			dispatcher.forward(request, response);
		}
	}

}
