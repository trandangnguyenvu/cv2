package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.Account;
import beans.User;

/*
 * LOGIN (WITH JNDI)
 */

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	public void init(ServletConfig config) throws ServletException {

		try {
			InitialContext initContext = new InitialContext();
			
			Context env = (Context)initContext.lookup("java:comp/env");
			
			ds = (DataSource)env.lookup("jdbc/ShoppingDB");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new ServletException();
		}
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");//++++++++ Vietnamese++++++++
		
		PrintWriter out = response.getWriter();
				
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		String action = request.getParameter("action");

		if (action == null) {
			session.setAttribute("error", "");
			request.getRequestDispatcher("/home.jsp?page=1").forward(request, response);
			;
		} else if (action.equals("dologin")) {
			
			Connection conn = null;
			
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regexPass = "[a-zA-Z0-9_!@#$%^&*]+";
			// [a-zA-Z0-9_!@#$%^&*]+
			// ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$

			String usr = request.getParameter("usr");
			String pwd = request.getParameter("pwd");

			String rem = request.getParameter("remember");

			if (rem == null) {
				rem = "";
			}
			
			
			
			
			
			//
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

			// out.println("Connected to database.");
			//

			
			
			
	
			Account account = new Account(conn);
				
				

				
				
			User acc = new User(usr, pwd);

			/* */

			if (!usr.matches(regexMail) || !pwd.matches(regexPass)) {
				session.setAttribute("error", "Username or password is invalid syntax");
				response.sendRedirect("login.jsp");
			} else {//else 1
				//String u = getServletContext().getInitParameter("userX"); 4 ADMIN
				//String p = getServletContext().getInitParameter("passX"); 4 ADMIN				
				
				try {
					
					if (account.login(usr, pwd) && rem.equals("rememberme")) {
						account.addToBean(usr, acc);
						//session.setAttribute("usr", acc.getUsr());
						// response.sendRedirect("web/admin/index.jsp");
						Cookie cookie = new Cookie("email", acc.getUsr());
						response.addCookie(cookie);
						// out.println("Cookie set.");	
						session.setAttribute("cartlink","");
						session.setAttribute("acc", acc.getName());
						session.setAttribute("login", "");
						session.setAttribute("register", "");
						session.setAttribute("logout", "Logout");
						
						session.setAttribute("address", acc.getAddress());
						session.setAttribute("mail", acc.getUsr());
						
						session.setAttribute("cartbean",null);
						
						session.setAttribute("cartlink","Cart of");
						request.getRequestDispatcher("/home.jsp").forward(request, response);

					} else if (account.login(usr, pwd)) {
						account.addToBean(usr, acc);
						//session.setAttribute("usr", acc.getUsr());
						session.setAttribute("cartlink","");
						session.setAttribute("acc", acc.getName());
						session.setAttribute("login", "");
						session.setAttribute("register", "");
						session.setAttribute("logout", "Logout");
						
						session.setAttribute("address", acc.getAddress());
						session.setAttribute("mail", acc.getUsr());
						
						session.setAttribute("cartbean",null);
						
						session.setAttribute("cartlink","Cart of");
						request.getRequestDispatcher("/home.jsp").forward(request, response);					
						
					
					} else { // else smallest
						
						session.setAttribute("error", "Wrong username or password");
						
						//session.setAttribute("cartbean",null);
						
						response.sendRedirect("login.jsp");

					}
		
				} catch (SQLException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					session.setAttribute("error", "Database error: phease try again later.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
					e.printStackTrace();
				} // =>else smallest

			} // =>else 1
			
			
			
			
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
			
			
		} // =>elseif

	} // => doPost

}
