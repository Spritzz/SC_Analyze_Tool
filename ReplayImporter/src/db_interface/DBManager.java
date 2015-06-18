package db_interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.PropertyLoader;

public class DBManager {
	  public static final DBManager INSTANCE = new DBManager();
	  private static String jdbcUrl;
	  
	  private DBManager(){
		  try {
			    Class.forName("com.mysql.jdbc.Driver");
		  } catch (ClassNotFoundException e) {
			    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		  }
	  }
	  
	  public void loadDBProperties() {
		  PropertyLoader config = new PropertyLoader("resources/config.properties");
		  String dbName = config.getProperty("dbName");
		  String userName = config.getProperty("userName");
		  String password = config.getProperty("password");
		  String hostname = config.getProperty("hostname");
		  String port = config.getProperty("port");		  
		  jdbcUrl = "jdbc:mysql://" + hostname + ":" +
				    port + "/" + dbName + "?user=" + userName + "&password=" + password;
	  }
	  
	  private Connection getConnection() {
		  Connection conn = null;
		  try{
			  loadDBProperties();
			  System.out.println("Making connection to " + jdbcUrl);
			  conn = DriverManager.getConnection(jdbcUrl);
		  } catch(SQLException ex) {
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());			  
		  } finally {
			    System.out.println("Closing the connection.");
			    if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
		  }
		  
		  return conn;
	  }
	  
	  public void importReplyData() {
		  Connection conn = getConnection();
		  try{			  
			  conn.close(); 
		  } catch(SQLException ex) {
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
		  } finally {
			    System.out.println("Closing the connection.");
			    if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
		  }
	  }
}
