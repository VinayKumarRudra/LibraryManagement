//$Id$
package library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LibraryAddBook {

	public static int addBook(Library lib) throws Exception {
		
		int value = 0;
		
		String url = "jdbc:mysql://localhost:3306/sample";
		String uname = "root";
		String pass = "vinay";
		 
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		Statement st = con.createStatement();
		
		String query = "select count(*) from temp2";  //to post book_id
		ResultSet rs = st.executeQuery(query);
		rs.next();
		int rows = rs.getInt(1);
		int book_id;
		int bookid;
		if(rows == 0) {
			book_id = 1001;
		}
		else {
			query = "select book_id from temp2 ORDER BY book_id DESC LIMIT 1";
			rs = st.executeQuery(query);
			rs.next();
			bookid = rs.getInt("book_id") ;
			book_id = bookid+1;
		}
		
		String book_name = lib.getName();  // to post book_name
		
		 
		query = "select count(*) from temp1";  // to post author id
		rs = st.executeQuery(query);
		rs.next();
		rows = rs.getInt(1);
		int author_id,authorid;
		if(rows == 0) {
			author_id = 2001;
		} else {
			query = "select author_id from temp1 ORDER BY author_id DESC LIMIT 1";
			rs = st.executeQuery(query);
			rs.next();
			authorid = rs.getInt("author_id");
			author_id = authorid+1;
		}
		
		String author_name = lib.getAuthor(); // to post author name
		
		String title = lib.getTitle(); // to post title
		
		int edition = Integer.parseInt(lib.getEdition()); // to post edition
		
		int pagenumber = Integer.parseInt(lib.getPageCount()); // to post page count
		
		String publisher = lib.getPublisher(); //to post publisher
		
		String publishedDate = lib.getPublishedDate(); // to post published date
		
		query = "select author_id from temp1 where author_name = '"+author_name+"'";
		rs = st.executeQuery(query);
		if(rs.next()) {				 // to check if author record exist.
			author_id = rs.getInt("author_id");
		} else {
			PreparedStatement psa = con.prepareStatement("insert into temp1 values(?,?)");
			psa.setInt(1,author_id);
			psa.setString(2,author_name);
			psa.executeUpdate();
			psa.close();
		}
		
		PreparedStatement psb = con.prepareStatement("insert into temp2 values(?,?,?,?,?,?,?,?)");
		psb.setInt(1,book_id);
		psb.setString(2,book_name);
		psb.setInt(3, author_id);
		psb.setString(4,title);
		psb.setInt(5,edition);
		psb.setInt(6,pagenumber);
		psb.setString(7,publisher);
		
		java.util.Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(publishedDate);
		java.sql.Date sqldate = new java.sql.Date(date1.getTime());
		
		psb.setDate(8,sqldate);
		
		int insert = psb.executeUpdate();
		int insertstatus = 0;
		
		if(insert > 0) {
			insertstatus =1;
			psb.close();
			st.close();
			con.close();
			return insertstatus;
		}
		
		return insertstatus;
	}

}
