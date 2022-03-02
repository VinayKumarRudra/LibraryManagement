//$Id$
package library;

import java.sql.*;

public class Samp {

	public static void main(String[] args) throws Exception {
		
		
		 String url = "jdbc:mysql://localhost:3306/sample";
		 String uname = "root";
		 String pass = "vinay";
		 String author_name = "vinay";
		 String key = "author_name";
		 
		 
		 
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con = DriverManager.getConnection(url,uname,pass);
		 PreparedStatement ps=con.prepareStatement("update temp1 set author_name = ? where author_name = ?");
		 //ps.setString(1,"author_name");
		 ps.setString(1,"vinay");
		 ps.setString(2,"sam");
		 
		 
			 //book_name = rs.getString("author_name");
		 
		 int status=ps.executeUpdate();  
		 
		 ps.close();
		 
		 con.close();
		 

	}

}
