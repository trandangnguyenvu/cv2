package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			HttpSession session = request.getSession();
			session.setAttribute("cartlink","");
			session.setAttribute("acc", "");
			session.setAttribute("login", "Login");
			session.setAttribute("register", "Register");
			session.setAttribute("logout", "");
			
			session.setAttribute("cartbean",null);
			
			request.getRequestDispatcher("/home.jsp?page=1").forward(request, response);
			//response.sendRedirect("home.jsp");
		} else if (action.equals("logout")) {
			HttpSession session = request.getSession();
			session.setAttribute("error","");
			session.setAttribute("cartbean",null);
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
