//$Id$
package library;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LibraryGetBookById {
	
	public static String getBook(String book_id) throws IOException, Exception {
		
		Library lib = new Library();
		
		String url = "jdbc:mysql://localhost:3306/sample";
		String uname = "root";
		String pass = "vinay";
		String author_name = "vinay";
		 
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		Statement st = con.createStatement();
		
		String query = "select book_id,book_name,author_name,title,edition,pagenumber,publisher,publishedDate from temp2 inner join temp1 using(author_id) where book_id ='"+book_id+"'";
		
		ResultSet rs = st.executeQuery(query);
		
		
		while(rs.next()) {
			int bookid = rs.getInt(1);
			String str = String.valueOf(bookid);
			lib.setBookId(str);
			
			lib.setName(rs.getString(2));
			
			lib.setAuthor(rs.getString(3));
			
			lib.setTitle(rs.getString(4));
			
			int edition = rs.getInt(5);
			str = String.valueOf(edition);
			lib.setEdition(str);
			
			int pagenumber = rs.getInt(6);
			str = String.valueOf(pagenumber);
			lib.setPageCount(str);
			
			lib.setPublisher(rs.getString(7));
			
			java.sql.Date sqldate = rs.getDate(8);
			str = sqldate.toString();
			lib.setPublishedDate(str);
			
			ObjectMapper obj = new ObjectMapper();
			String json =  obj.writeValueAsString(lib);
			return json;
			
		}
		return null;
	}
}
