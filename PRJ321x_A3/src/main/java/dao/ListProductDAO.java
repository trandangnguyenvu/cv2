package dao;

import model.Product; 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.DBContext;



/**
 * Servlet implementation class ListProductDAO
 */
public class ListProductDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProductDAO() {
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
		/*
		 * 
		 * 		 
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch (ClassNotFoundException e) {
			out.println("Can't load database driver.");
		}
		
		
		
		Connection conn = null;
		try {
			
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=70-461;encrypt=false;", "sa", "12345");
		} catch (Exception e) {
			out.println("can't connect to data.");
			e.printStackTrace();
			return;
		}
		
		//out.println("Connected to database.");

		try {
			conn.close();
		} catch (Exception e) {
			
		}
		
		*
		*
		*/
	}
	
	
	
    public static Connection getConnection(){    	
 
    	/*
    	 * WAY 1: making a local method for connection
    	 */  
    	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
		} catch (ClassNotFoundException e) {
			//System.out.println("Can't load database driver.");
		}
		
		
		
		Connection conn = null;
		try {			
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ShoppingDB;encrypt=false;", "sa", "12345");
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		return conn;
		   	
    				
		/*
		try {
			conn.close();
		} catch (Exception e) {
			
		}
		*/		
    }
	
    
    /**
     * WITH pagination
	 *
     * @param start
     * @param total
     * @return
     */
    public static List<Product> getRecords(int start,int total){  
        List<Product> list=new ArrayList<Product>();  
        try{  
            /*
             * for way 1 : using a local method of connection (above method)
             */ 
        	//Connection con=getConnection();
        	
        	
        	/*
        	 * way 2 : get connection from a class
        	 */
        	Connection con= new DBContext().getConnection();
        	
            
            PreparedStatement ps=con.prepareStatement("select * from Products order by product_id, product_name "
            		+ "offset " + start + " rows fetch next " + total + " rows only");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	Product e=new Product();  
            	
                e.setId(rs.getInt("product_id"));  
                e.setName(rs.getString("product_name")); 
                e.setDescription(rs.getString("product_des"));                                 
                e.setPrice(rs.getFloat("product_price"));
                e.setSrc(rs.getString("product_img_source"));
                e.setType(rs.getString("product_type"));
                e.setBrand(rs.getString("product_brand"));
                
                list.add(e);  
            }  
            con.close();  
        }catch(Exception e){System.out.println(e);}  
        return list;  
    }  

    
    /**
     * WITHOUT pagination
	 *
     * @param start
     * @param total
     * @return
     */
    public static List<Product> getAllRecords(){  //static
        List<Product> list=new ArrayList<Product>();  
        try{  
            /*
             * for way 1 : using a local method of connection (above method)
             */ 
        	//Connection con=getConnection();
        	
        	
        	/*
        	 * way 2 : get connection from a class
        	 */
        	Connection con= new DBContext().getConnection();
        	
            
            PreparedStatement ps=con.prepareStatement("select * from Products");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	Product e=new Product();  
            	
                e.setId(rs.getInt("product_id"));  
                e.setName(rs.getString("product_name")); 
                e.setDescription(rs.getString("product_des"));                                 
                e.setPrice(rs.getFloat("product_price"));
                e.setSrc(rs.getString("product_img_source"));
                e.setType(rs.getString("product_type"));
                e.setBrand(rs.getString("product_brand"));
                
                list.add(e);  
            }  
            con.close();  
        }catch(Exception e){System.out.println(e);}  
        return list;  
    }  
    
    
    
    
    public List<Product> getAllRecords2(){  //static
        List<Product> list=new ArrayList<Product>();  
        try{  
            /*
             * for way 1 : using a local method of connection (above method)
             */ 
        	//Connection con=getConnection();
        	
        	
        	/*
        	 * way 2 : get connection from a class
        	 */
        	Connection con= new DBContext().getConnection();
        	
            
            PreparedStatement ps=con.prepareStatement("select * from Products");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	Product e=new Product();  
            	
                e.setId(rs.getInt("product_id"));  
                e.setName(rs.getString("product_name")); 
                e.setDescription(rs.getString("product_des"));                                 
                e.setPrice(rs.getFloat("product_price"));
                e.setSrc(rs.getString("product_img_source"));
                e.setType(rs.getString("product_type"));
                e.setBrand(rs.getString("product_brand"));
                
                list.add(e);  
            }  
            con.close();  
        }catch(Exception e){System.out.println(e);}  
        return list;  
    }  
    
    
	    
    
    public List<Product> search(String characters) {
    	List<Product> list=ListProductDAO.getAllRecords(); 
    	
    	List<Product> listSearch= new ArrayList<Product>();
    	
    	for (Product e:list) {
    		// not compareToIgnoreCase
    		if (e.getName().toLowerCase().contains(characters.toLowerCase())) {
    			listSearch.add(e);
    		}
    	}
    	return listSearch;
    }
    
    
    public Product get1Record (int id) {
    	List<Product> list=ListProductDAO.getAllRecords();
    	Product p = null;
    	for (Product e:list) {
    		if (id == e.getId()) {
    			 p = e;
    		}
    	}
    	return p;
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	

}
