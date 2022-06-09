package controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListProductDAO;
import model.Cart;
import model.Product;

/**
 * Servlet implementation class AddToCartController
 */
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartController() {
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
		//request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// +++++ ----- VIETNAMESE ----- +++++ 
		response.setContentType("text/html;charset=UTF-8");
		//request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		
		//if (session.getAttribute("mail") == null) : logout after login => it still allow to add to cart => wrong 
		if (session.getAttribute("acc").equals("")) {
			session.setAttribute("error", "You must login first.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else {// else 1
			try {		
				
				
				//HttpSession session = request.getSession(true);
				
				String action = request.getParameter("action");
				String idS = request.getParameter("image");				
				
				int id = Integer.parseInt(idS);
				//session.setAttribute("idproduct", id);// for cart to database => false => delete
				
				if (action != null && action.equals("cart")) {
					if (session.getAttribute("cartbean") == null) {
						//request.getAttribute("cartbean") == null 24/5-1
						session.setAttribute("cartbean", new Cart()); 
						//request.setAttribute("cartbean", new Cart()); 24/5-2
					}
					Product p = new ListProductDAO().get1Record(id);
					Cart c = (Cart) session.getAttribute("cartbean"); 
					//Cart c = (Cart) request.getAttribute("cartbean"); 24/5-3
					
					//c.add(p); 
					c.add(new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getSrc(), p.getType(), p.getBrand(), 1));
					
					session.setAttribute("cartbean", c); 
					//request.setAttribute("cartbean", c); 24/5-4
				}
				else if (action != null && action.equals("delete")) {
					Cart c = (Cart) session.getAttribute("cartbean"); 
					//Cart c = (Cart) request.getAttribute("cartbean"); 24/5-5
					
					c.remove(id); 
					session.setAttribute("cartbean", c);
				}

				//response.sendRedirect("home.jsp");			
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			
			
			} catch (Exception ex){
				response.getWriter().println(ex);
			}
		}// => else 1
		

	}

}
