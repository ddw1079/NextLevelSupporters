package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectiontest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//접속 개체 선언
				Connection con1 = null;

				Class.forName("oracle.jdbc.driver.OracleDriver");

				con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","nlsdba","nextlevelsupporters");//접속

				System.out.println("접속 확인 테스트 문장");
				
}
	}

