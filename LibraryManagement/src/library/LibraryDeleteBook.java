//$Id$
package library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LibraryDeleteBook {

	public static int deleteBook(String book_id) throws IOException, Exception {
		
Library lib = new Library();
		
		String url = "jdbc:mysql://localhost:3306/sample";
		String uname = "root";
		String pass = "vinay";
		String author_name = "vinay";
		 
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		
		int status = 0;
		
		String query = "select author_id from temp2 where book_id ='"+book_id+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			
			int author_id = rs.getInt(1);
			PreparedStatement ps=con.prepareStatement("delete from temp2 where book_id =?");
			ps.setString(1,book_id);
			int val = ps.executeUpdate();
			status =1;
			
			query = "select author_id from temp2 where author_id ='"+author_id+"'";
			rs = st.executeQuery(query);
			int count =0;
			while(rs.next()) {
				count++;
			}
			
			if(count>0) {
				
			} else {
				ps=con.prepareStatement("delete from temp1 where author_id =?");
				ps.setInt(1,author_id);
				val = ps.executeUpdate();
				status =1;
			}
			
		}else {
			status = 0;
		}

		return status;
	}

}
