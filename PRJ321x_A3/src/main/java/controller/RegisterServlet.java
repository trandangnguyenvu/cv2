package controller;

import java.io.IOException;
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
 * REGISTING (WITH JNDI)
 */


/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		
		String action = request.getParameter("action");

		if (action == null) {
			session.setAttribute("error", "");
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			;
		} else if (action.equals("doregister")) {
			
			Connection conn = null;
			
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regexPass = "[a-zA-Z0-9_!@#$%^&*]+";
			// [a-zA-Z0-9_!@#$%^&*]+
			// ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$

			String usr = request.getParameter("usr");
			String pwd = request.getParameter("pwd");
			String rpwd = request.getParameter("rpwd"); 
			String rl = request.getParameter("rl");
			String nm = request.getParameter("nm"); 
			String adrs = request.getParameter("adrs"); 
			String phn = request.getParameter("phn"); 
		
			
			
			
			
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
			if (nm == null || nm == "" || rl == null || rl == "") {				
				session.setAttribute("usr", acc.getUsr());		
				session.setAttribute("error", "User name or user role is empty!");
				request.getRequestDispatcher("/register.jsp").forward(request, response);	
			}
			
			
			else if (!pwd.equals(rpwd)) {
				//password don't match				
				session.setAttribute("usr", acc.getUsr());
				session.setAttribute("error", "Passwords do not match!");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
			
			
			//Integer.valueOf() freecodecamp
			else if ( Integer.parseInt(rl) != 1  && Integer.parseInt(rl) != 2 ) {
				session.setAttribute("usr", acc.getUsr());
				session.setAttribute("error", "The value of your role must be 1 or 2.");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
			
			
			else if (!usr.matches(regexMail) || !pwd.matches(regexPass)) {
				session.setAttribute("error", "Username or password is invalid syntax.");
				session.setAttribute("usr", acc.getUsr());
				response.sendRedirect("register.jsp");
			} else {//else 1
				//String u = getServletContext().getInitParameter("userX"); 4 ADMIN
				//String p = getServletContext().getInitParameter("passX"); 4 ADMIN				
				
				try {
					if (account.existsAdmin(rl) && rl.equals("2")) {
						session.setAttribute("error", "You can not be admin!");
						session.setAttribute("usr", acc.getUsr());
						request.getRequestDispatcher("/register.jsp").forward(request, response);	
					}
					
					else if (account.exists(usr)) {
						session.setAttribute("error", "An account with this email has already exists!");
						session.setAttribute("usr", acc.getUsr());
						request.getRequestDispatcher("/register.jsp").forward(request, response);	
					} else {
						// we create the account.
						account.create(usr, pwd, rl, nm, adrs, phn);
						request.getRequestDispatcher("/createsuccess.jsp").forward(request, response);	
					}
				} catch (SQLException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

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
