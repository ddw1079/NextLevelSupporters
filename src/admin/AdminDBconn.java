package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminDBconn {
	private Connection con;
	
	public Connection getConnection() {
	return con;
}
	
	
	public AdminDBconn() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","nlsdba","nextlevelsupporters");
	}
	
}	
