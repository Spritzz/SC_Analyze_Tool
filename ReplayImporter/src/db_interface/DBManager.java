package db_interface;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import utils.PropertyLoader;

public class DBManager {
	  public static final DBManager INSTANCE = new DBManager();
	  private static String dbName = "ReplayData";
	  private static String userName = "";
	  private static String password = "";
	  private static String hostname = "";
	  private static String port = "3306";
	  private static String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
	    port + "/" + dbName + "?user=" + userName + "&password=" + password;
	  
	  private DBManager(){
		  try {
			    Class.forName("com.mysql.jdbc.Driver");
		  } catch (ClassNotFoundException e) {
			    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		  }
	  }
	  
	  public static void loadDBProperties() {
		  PropertyLoader config = new PropertyLoader("resources/config.properties");
		  dbName = config.getProperty("dbName");
		  userName = config.getProperty("userName");
		  password = config.getProperty("password");
		  hostname = config.getProperty("hostname");
		  port = config.getProperty("port");
	  }
	  
	  private Connection getConnection() {
		  Connection conn = null;
		  try{
			  loadDBProperties();
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
