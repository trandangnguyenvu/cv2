package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import beans.User;
import context.DatasourceDemo;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 
		 *
		String action = request.getParameter("action");
		if (action == null) {
			request.getRequestDispatcher("/home.jsp").forward(request, response);;
		} else if (action.equals("login")) {
			HttpSession session = request.getSession();
			//session.setMaxInactiveInterval(5000);
			session.setAttribute("usr","");
			session.setAttribute("error","");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		*
		*
		*/
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		

		
		String action = request.getParameter("action");
		if (action == null) {
			session.setAttribute("error","");
			request.getRequestDispatcher("/home.jsp").forward(request, response);;
		} else if (action.equals("dologin")) {
			
			
			try {				

				
				String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
				String regexPass = "[a-zA-Z0-9_!@#$%^&*]+";
				//[a-zA-Z0-9_!@#$%^&*]+
				//^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$
				
				String usr = request.getParameter("usr");
				String pwd = request.getParameter("pwd");
				 
				String rem = request.getParameter("remember");
				if (rem == null) {
					rem = "";
				}
				
								
				//session.setAttribute("usr", usr);	
				//session.setAttribute("rem", rem);
				
				User acc = new User(usr,pwd);
				
				/* */
				
				if (!usr.matches(regexMail) || !pwd.matches(regexPass) )  {
					session.setAttribute("error", "Username or password is invalid syntax");					
					response.sendRedirect("login.jsp");
				} else {
					String u = getServletContext().getInitParameter("userX");
					String p = getServletContext().getInitParameter("passX"); 	
					
					
					if (usr != null && acc.getPwd().equals(p) && acc.getUsr().equalsIgnoreCase(u) && rem.equals("rememberme")) {

						session.setAttribute("usr", acc.getUsr());
						//response.sendRedirect("web/admin/index.jsp");
						Cookie cookie = new Cookie("email", acc.getUsr());
						response.addCookie(cookie);
						//out.println("Cookie set.");
						request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
						
					} else if (usr != null && acc.getPwd().equals(p) && acc.getUsr().equalsIgnoreCase(u)) {
						session.setAttribute("usr", acc.getUsr());
						request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
					} else {
						session.setAttribute("error", "Wrong username or password");
						response.sendRedirect("login.jsp");
						/*response.getWriter().println("<b style='color:red;''>Username or password is invalid</b>");*/
					
					} //>else
				} //>else	
				
			} catch (NullPointerException e) {			
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} catch(Exception ex) {
				response.getWriter().println(ex);
			}
			
			
		}//>elseif

	}

}
