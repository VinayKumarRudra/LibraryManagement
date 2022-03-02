//$Id$
package library;

import java.sql.*;

public class DbConnection {

	public static Statement getDbObject() throws Exception {
		String url = "jdbc:mysql://localhost:3306/sample";
		 String uname = "root";
		 String pass = "vinay";
		 
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con = DriverManager.getConnection(url,uname,pass);
		 Statement st = con.createStatement();
		 
		 return st;
	}
}
