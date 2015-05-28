package db_interface;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
	  public static final DBManager INSTANCE = new DBManager();
	  private static final String dbName = "ReplayData";
	  private static String userName = "";
	  private static String password = "";
	  private static String hostname = "";
	  private static final String port = "3306";
	  private static final String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
	    port + "/" + dbName + "?user=" + userName + "&password=" + password;
	  
	  private DBManager(){
		  try {
			    Class.forName("com.mysql.jdbc.Driver");
		  } catch (ClassNotFoundException e) {
			    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		  }
	  }
	  
	  private Properties getDBProperties() {
		  Properties props = new Properties();
		  try{
			  FileInputStream in = new FileInputStream("/db.properties");
			  props.load(in);
			  in.close();
		  } catch(Exception e) {
			  
		  }
		   return props;
	  }
	  private Connection getConnection() {
		  Connection conn = null;
		  try{
			  Properties props = getDBProperties();
			  System.out.println( props);
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
