package beans;

import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
	private Connection conn;
	
	public Account(Connection conn) {
		this.conn = conn;
	}	
	
	/*
	 * checking for login method
	 */
	public boolean login(String email, String password) throws SQLException {
		
		String sql = "select count(*) as count from Account where user_mail=? and password=?";
		
		
		// only 1 in 2 : or : throws SQLException, or try-catch :
		//try {
			PreparedStatement stmt = conn.prepareStatement(sql);
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		stmt.setString(1, email);
		stmt.setString(2, password);
		
		ResultSet rs = stmt.executeQuery();
		// will return ResultSet object
		
		
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
	
	
	
	
	
	
	
	/*
	 * account Creating
	 */
	public void create (String email, String password, String role, String name, String address, String phone) throws SQLException  {
		
		String sql = "insert into Account (user_mail, password, account_role, user_name, user_address, user_phone) values (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		int r =Integer.parseInt(role);
		
		stmt.setString(1, email);
		stmt.setString(2, password);
		stmt.setLong(3, r);
		stmt.setString(4, name);
		stmt.setString(5, address);
		stmt.setString(6, phone);
		
		stmt.executeUpdate();
		
		stmt.close();		
	}
	
	
	
	
	
	
	
	/*
	 * is exists the account?
	 */
	public boolean exists(String email) throws SQLException {
		
		String sql = "select count(*) as count from Account where user_mail=?";
		
		//try {
			PreparedStatement stmt = conn.prepareStatement(sql);
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		stmt.setString(1, email);
		
		
		ResultSet rs = stmt.executeQuery();
		// will return ResultSet object
		
		
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

	
	
	public boolean existsAdmin (String rl) throws SQLException {
		String sql = "select count(*) as count from Account where account_role=?";
		
		//try {
			PreparedStatement stmt = conn.prepareStatement(sql);
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		int r =Integer.parseInt(rl);
			

		stmt.setLong(1, r);				
		
		
		ResultSet rs = stmt.executeQuery();
		// will return ResultSet object
		
		
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
	
	
	
	
	
	
	
	public void addToBean(String email,User user) throws SQLException {
		
		String sql = "select * from Account where user_mail=?";
		
		//try {
			PreparedStatement stmt = conn.prepareStatement(sql);
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		stmt.setString(1, email);
		
		
		ResultSet rs = stmt.executeQuery();
		// will return ResultSet object
		
				
		//rs.close();	
		
		if (rs.next()) {
			String nm = rs.getNString("user_name");
			String adrs = rs.getNString("user_address");
			String mail = rs.getString("user_mail");
			
			user.setName(nm);
			user.setAddress(adrs);
			user.setUsr(mail);
		}
		
		

		rs.close();
	}
	
	
	
	
	
	
	
	
	public void infoToBean (String email, User user) throws SQLException {
		String sql = "select * from Account";		
		
		PreparedStatement stmt = conn.prepareStatement(sql);		
						
		ResultSet rs = stmt.executeQuery();			
				
		//rs.close();	
		
		if (rs.next()) {
			String nm = rs.getNString("user_name");
			user.setName(nm);
		}
		
		

		rs.close();
	}
	
	
	
	
}
