package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import context.DBContext;
import model.Cart;
import model.CartBig;
import model.Orders;
import model.Product;
import model.ProductOrders;

public class OrdersDAO {
	public boolean exists(int order_id) throws SQLException {
		Connection conn= new DBContext().getConnection();
		String sql = "select count(*) as count from Orders_detail where order_id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setLong(1, order_id);
		
		ResultSet rs = stmt.executeQuery();
		
		int count =0;
		if (rs.next()) {
			// if rsnext is true, mean khong rong at 16:5 of video Querying database - van tin data source (f3-bai9) :
			count = rs.getInt("count");	
		}
		
		rs.close();
		
		if (count == 0) {
			return false;
		} else {
			return true;
		}	
		
	}
	
	
	
	
	public int getMaxOrderId(Orders o) throws SQLException { //=> << OK >> from test 1 at PayController servlet
		Connection conn= new DBContext().getConnection();
		String sql = "select max(order_id) as maxorderid from Orders where user_mail=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, o.getUserMail());
		
		ResultSet rs = stmt.executeQuery();
		
		
		
		int id = 0;
		if (rs.next()) {
			id = rs.getInt("maxorderid");			
		}
		
		rs.close();
		return id;
	}	
	
	
	
	
	public boolean orderIdisEmpty() throws SQLException { //=> << OK >> from test 2 at PayController servlet
		Connection conn= new DBContext().getConnection();
		String sql = "select order_id from Orders_detail";
		
		PreparedStatement stmt = conn.prepareStatement(sql);	
		
		ResultSet rs = stmt.executeQuery();
		
		
		if (rs.next()) {
			// if rsnext is true, mean khong rong at 16:5 of video Querying database - van tin data source (f3-bai9) :
			if (rs != null) {
				return false;
			}
		}
		rs.close();	
		return true;				

	}
	
	

	
	
	public void insertOrder(Orders o, Cart c) throws Exception {
		//Connection conn= new DBContext().getConnection();		
		/*
		if (orderIdisEmpty()) {
			o.setOrderID(1);
		}
		else {
			int maxId = getMaxOrderId();
			o.setOrderID(maxId + 1);
		}
		*/
		
		
		Connection conn= new DBContext().getConnection();
		
		
		//insert into Orders (user_mail, order_id, order_status, order_date, order_discount_code, order_address)
		String sql = "insert into Orders (user_mail, order_address) values (?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, o.getUserMail());		
		//stmt.setDate(4, java.sql.Date.valueOf("2022-01-01"));		
		stmt.setNString(2, o.getAddress());
				
		stmt.executeUpdate();
		
		/* ask 98 */
		
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                o.setOrderID(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        
        //stmt.executeUpdate();
        
		
		
		
		//int maxid = getMaxOrderId(o);			
				
		for (Product p : c.getItems()) {
			
			sql = "insert into Orders_detail (order_id, product_id, amount_product, price_product) values (?, ?, ?, ?)";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, o.getOrderID());
			//stmt.setLong(1, maxid);
			stmt.setLong(2, p.getId());
			stmt.setLong(3, p.getNumber());
			stmt.setLong(4, (long) p.getPrice());
		
			stmt.executeUpdate();			
		}
		
		
		stmt.close();
	}
	

	public Orders callCart (String mail) throws Exception {// void
		Connection conn= new DBContext().getConnection();
	
		
		
		String sql = "select top 1 * from Orders where user_mail=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, mail);
		ResultSet rs = stmt.executeQuery();	
		Orders o = new Orders();
		if (rs.next()) {
			

			o.setUserMail(rs.getString("user_mail"));
			o.setAddress(rs.getNString("order_address"));
			o.setOrderID(rs.getInt("order_id"));
		}
		
		
		
		
		
		sql = "select * from  Orders_detail";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		List<ProductOrders> listPO = new ArrayList<ProductOrders>();
		while(rs.next()){ 			
			ProductOrders po = new ProductOrders();
			
			po.setOrderID(rs.getInt("order_id"));
			po.setProductID(rs.getInt("product_id"));	
			po.setAmountProduct(rs.getInt("amount_product"));
			po.setPrice(rs.getInt("price_product"));
			
			listPO.add(po);
		}
		
		Cart c = new Cart();
		List<Product> allRecords = new ListProductDAO().getAllRecords2();
		for (ProductOrders po:listPO) {
			Product p = new Product();
			p.setId(po.getProductID());
			p.setNumber(po.getAmountProduct());
			
			int productId = po.getProductID();
			for (Product all: allRecords) {
				if(productId == all.getId()) {
					p.setName(all.getName());
					p.setPrice(all.getPrice());
				}
					
			}
			
			c.add(p);			
		}		
			
				
		conn.close();  
		return o;
	}
	
	
	
	
	// return object of Cart contain products
	public Cart callCartReturnCart (String mail) throws Exception {// void
		Connection conn= new DBContext().getConnection();
		
		//select top 1 * from Orders where user_mail=?
		String sql = "select * from Orders where user_mail=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, mail);
		
		ResultSet rs = stmt.executeQuery();
		
		//List<Product> list=new ArrayList<Product>();
		//Cart c = new Cart();
		
		
		
		
		
		// take db from ---- Orders table ----, then insert them into List listO
		List<Orders> listO = new ArrayList<Orders>();
		
		while(rs.next()){  
			 Orders o = new Orders(); 			 
         	
             o.setUserMail(rs.getString("user_mail"));
			 o.setAddress(rs.getNString("order_address"));   
			 o.setOrderID(rs.getInt("order_id"));
             
             listO.add(o);   
        }
  
		
		
		
		
		// take order_id(s) of the mail 
		List<Integer> listOrderId = new ArrayList<Integer>();
		for (Orders o:listO) {
			if(o.getUserMail().equals(mail)) {
				int orderId = o.getOrderID();				
				listOrderId.add(orderId);
			}
		}
		
		
		
		
		
		// take db from ---- Orders_detail ---- , then insert them into List listPO
		sql = "select * from  Orders_detail";		
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		List<ProductOrders> listPO = new ArrayList<ProductOrders>();
		while(rs.next()){ 			
			ProductOrders po = new ProductOrders();
			
			po.setOrderID(rs.getInt("order_id"));
			po.setProductID(rs.getInt("product_id"));	
			po.setAmountProduct(rs.getInt("amount_product"));
			po.setPrice(rs.getInt("price_product"));
			
			listPO.add(po);
		}
		
				
		
		
		
		// make list of all products those user submited
		Cart c = new Cart();
		List<Product> allRecords = new ListProductDAO().getAllRecords2();
		for(Integer i:listOrderId) {
			for (ProductOrders po:listPO) {
				if (i == po.getOrderID()) {
					Product p = new Product();
					p.setId(po.getProductID());
					p.setNumber(po.getAmountProduct());
					
					int productId = po.getProductID();
					for (Product all: allRecords) {
						if(productId == all.getId()) {
							p.setName(all.getName());
							p.setPrice(all.getPrice());
						}							
					}
					c.add(p);
				}				
			}
		}
		
		conn.close();  
		return c;	
	}
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////
	// return object contain orders
	public CartBig callCartReturnCart4Order (String mail) throws Exception {
		Connection conn= new DBContext().getConnection();
		
		//select top 1 * from Orders where user_mail=?
		String sql = "select * from Orders where user_mail=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, mail);
		
		ResultSet rs = stmt.executeQuery();
		
		//List<Product> list=new ArrayList<Product>();
		//Cart c = new Cart();
		
		
		
		
		
		// take db from ---- Orders table ----, then insert them into List listO
		List<Orders> listO = new ArrayList<Orders>();
		
		while(rs.next()){  
			 Orders o = new Orders(); 			 
         	
             o.setUserMail(rs.getString("user_mail"));
			 o.setAddress(rs.getNString("order_address"));   
			 o.setOrderID(rs.getInt("order_id"));
             
             listO.add(o);   
        }
 	
		

		
		
		
		
		// take db from ---- Orders_detail ---- , then insert them into List listPO
		sql = "select * from  Orders_detail";		
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		List<ProductOrders> listPO = new ArrayList<ProductOrders>();
		while(rs.next()){ 			
			ProductOrders po = new ProductOrders();
			
			po.setOrderID(rs.getInt("order_id"));
			po.setProductID(rs.getInt("product_id"));	
			po.setAmountProduct(rs.getInt("amount_product"));
			po.setPrice(rs.getInt("price_product"));
			
			listPO.add(po);
		}
		
				
		
		
		
		// make list of all products those user submited	
		//Cart c = new Cart(); THIS POSITION IS WRONG
		CartBig cb = new CartBig(); 
		List<Product> allRecords = new ListProductDAO().getAllRecords2();
		for(Orders o:listO) {
			Cart c = new Cart();
			for (ProductOrders po:listPO) {				
				if (o.getOrderID() == po.getOrderID()) {
					
					Product p = new Product();
					p.setId(po.getProductID());
					p.setNumber(po.getAmountProduct());
					
					int productId = po.getProductID();
					for (Product all: allRecords) {
						if(productId == all.getId()) {
							p.setName(all.getName());
							p.setPrice(all.getPrice());
						}
						
					}
					c.add(p);	
					
				}
				
			}
			cb.add(c);			
		}
		
		conn.close();  
		return cb;
		
	}
	
	
	
	
	
	
}





