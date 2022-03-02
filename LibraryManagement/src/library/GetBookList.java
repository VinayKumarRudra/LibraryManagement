//$Id$
package library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GetBookList {
	public static List map_book = new ArrayList();
	public static int row_number;
	
	public static void getBookList() throws IOException, Exception {
		Library lib = new Library();
		
		String url = "jdbc:mysql://localhost:3306/sample";
		String uname = "root";
		String pass = "vinay";
		 
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		Statement st = con.createStatement();
		
		String query = "select count(*) from temp2";
		ResultSet res = st.executeQuery(query);
		res.next();
		row_number = res.getInt(1);
		
		query = "select book_id,book_name,author_name,title,edition,pagenumber,publisher,publishedDate from temp2 left join temp1 on temp2.author_id=temp1.author_id";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			int book_id = rs.getInt(1);
			String str = String.valueOf(book_id);
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
			
			map_book.add(json);
		}
		st.close();
		con.close();
	}
}
