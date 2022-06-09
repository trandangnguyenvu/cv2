package controller;

import java.util.*;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDAO;
import dao.OrdersDAO;
import model.Cart;
import model.Orders;
import model.Product;

/**
 * Servlet implementation class PayController
 */
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// +++++ ----- VIETNAMESE ----- +++++
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {

			HttpSession session = request.getSession(true);
			
			//session.getAttribute("cartbean") == null
			if (session.getAttribute("cartbean") == null) {
				// request.getAttribute("cartbean") == null 24/5-1
			} else {
				OrdersDAO dao = new OrdersDAO();

				Cart c = (Cart) session.getAttribute("cartbean");

				// get param from params : usr - pwd - nm - adrs
				// get Attribute from Attribute : session.getAttribute("acc") => from :
				// LoginServlet : session.setAttribute("acc", acc.getName());
				// _ session.setAttribute("mail", acc.getUsr());
				// _ session.setAttribute("address", acc.getAddress());
				String username = (String) session.getAttribute("acc");
				String address = (String) session.getAttribute("address");
				String usermail = (String) session.getAttribute("mail");
				
				Orders o = new Orders(address, usermail);
				
				dao.insertOrder(o, c);
				
				session.setAttribute("cartbean",null);
				
				// test 1 => << OK >>
				/*
				int max = dao.getMaxOrderId(o);
				session.setAttribute("max", max);
				request.getRequestDispatcher("/testgetpaycontroller.jsp").forward(request, response);
				*/
				// => test 1
				
				
				// test 2 => << OK >>
				/*
				if (dao.orderIdisEmpty()) {
					session.setAttribute("orderisempty", "empty");
				}
				else {
					session.setAttribute("orderisempty", "not empty");
				}
				request.getRequestDispatcher("/testgetpaycontroller.jsp").forward(request, response);
				*/
				// => test 2
				
				
				session.setAttribute("cartlink","Cart of");
				request.getRequestDispatcher("/home.jsp?page=1").forward(request, response);
				// response.sendRedirect("home.jsp");
			}
		} catch (Exception ex) {
			response.getWriter().println(ex);
			ex.printStackTrace();
		}
	}

	

}
