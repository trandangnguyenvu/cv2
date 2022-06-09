package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	private final String serverName = "localhost";
	private final String dbName = "ShoppingDB";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "12345";
	
	public Connection getConnection () /*throws Exception -- 4 WAY 1*/ {
		/*WAY 1
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName="+dbName;
		// ";encrypt=false;"
		
		if (instance == null || instance.trim().isEmpty()) 
			url = "jdbc:sqlserver://"+serverName+":"+portNumber+";databaseName="+dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
		*/
		
		/*
		 * WAY 2
		 */
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName="+dbName + ";encrypt=false;";
		
		
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
		} catch (ClassNotFoundException e) {			
		}
		
		
		
		Connection conn = null;
		try {			
			conn = DriverManager.getConnection(url, userID, password);
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		return conn;	
	}
}
