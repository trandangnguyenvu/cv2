package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");//++++++++ Vietnamese++++++++
		HttpSession session = request.getSession();		
		
		
		String action = request.getParameter("action");
		if (action == null) {
			//HttpSession session = request.getSession();
			session.setAttribute("cartlink","");
			session.setAttribute("acc", "");
			session.setAttribute("login", "Login");
			session.setAttribute("register", "Register");
			session.setAttribute("logout", "");
			request.getRequestDispatcher("/home.jsp?page=1").forward(request, response);
		} 
		else if (action.equals("login")) {
			//HttpSession session = request.getSession();
			//session.setMaxInactiveInterval(5000);
			session.setAttribute("usr","");
			session.setAttribute("error","");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else if (action.equals("register")) {
			//HttpSession session = request.getSession();
			//session.setMaxInactiveInterval(5000);
			session.setAttribute("usr","");
			session.setAttribute("error","");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		else if (action.equals("image")) {
			request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		/* 2
		else if (action.equals("cart")) {
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
		=> 2 */
		
		/* 1
		else if (action.equals("dosearch")) {
			//request.getRequestDispatcher("/search.jsp").forward(request, response); /
			
		}
		=> 1 */
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
