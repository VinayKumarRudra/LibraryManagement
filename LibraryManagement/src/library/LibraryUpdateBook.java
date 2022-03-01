//$Id$
package library;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LibraryUpdateBook {

	public static int updateBook(String book_id,String key,Library lib) throws IOException, Exception {
		
		int status = 0;
		
		String url = "jdbc:mysql://localhost:3306/sample";
		String uname = "root";
		String pass = "vinay";
		 
		int index = Index.getIndex(lib, key);
		System.out.println(key+"  "+index);
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		
		if(key.equals("author")) {
			String value = UpdateLibValue.getValue(index, lib);
			
			String query = "select author_id from temp2 where book_id ='"+book_id+"'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				int authorid = rs.getInt(1);
				System.out.println(authorid);
				String str = String.valueOf(authorid);
				PreparedStatement ps=con.prepareStatement("update temp1 set author_name =? where author_id =?");
				ps.setString(1,value);
				ps.setString(2,str);
				status=ps.executeUpdate();
				ps.close();
			} else {
				status = 0;
			}
			
			
		}  
		
		if(key.equals("bookId")) {
			String value = UpdateLibValue.getValue(index, lib);
			PreparedStatement ps=con.prepareStatement("update temp2 set book_id =? where book_id =?");
			int num = Integer.parseInt(value);
			ps.setInt(1,num);
			ps.setString(2,book_id);
			status=ps.executeUpdate();
			ps.close();
		}  
		
		if(key.equals("name")) {
			String value = UpdateLibValue.getValue(index, lib);
			PreparedStatement ps=con.prepareStatement("update temp2 set book_name =? where book_id =?");
			ps.setString(1,value);
			ps.setString(2,book_id);
			status=ps.executeUpdate();
			ps.close();
		}
		
		if(key.equals("title")) {
			String value = UpdateLibValue.getValue(index, lib);
			PreparedStatement ps=con.prepareStatement("update temp2 set title =? where book_id =?");
			ps.setString(1,value);
			ps.setString(2,book_id);
			status=ps.executeUpdate();
			ps.close();
		}
		
		if(key.equals("edition")) {
			String value = UpdateLibValue.getValue(index, lib);
			PreparedStatement ps=con.prepareStatement("update temp2 set edition =? where book_id =?");
			int num = Integer.parseInt(value);
			ps.setInt(1,num);
			ps.setString(2,book_id);
			status=ps.executeUpdate();
			ps.close();
		} 
		
		if(key.equals("pageCount")) {
			String value = UpdateLibValue.getValue(index, lib);
			PreparedStatement ps=con.prepareStatement("update temp2 set pagenumber =? where book_id =?");
			int num = Integer.parseInt(value);
			ps.setInt(1,num);
			ps.setString(2,book_id);
			status=ps.executeUpdate();
			ps.close();
		}
		
		if(key.equals("publisher")) {
			String value = UpdateLibValue.getValue(index, lib);
			System.out.println(value);
			PreparedStatement ps=con.prepareStatement("update temp2 set publisher =? where book_id =?");
			ps.setString(1,value);
			ps.setString(2,book_id);
			status=ps.executeUpdate();
			ps.close();
		}
		
		if (key.equals("publishedDate")) {
			String date = UpdateLibValue.getValue(index, lib);
			java.util.Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(date);
			java.sql.Date value = new java.sql.Date(date1.getTime());
			PreparedStatement ps=con.prepareStatement("update temp2 set publishedDate =? where book_id =?");
			ps.setDate(1,value);
			ps.setString(2,book_id);
			status=ps.executeUpdate();
			ps.close();
		} 
		
		con.close();
		
		int res = 0;
		
		if(status>0) {
			res = 1;
		} else {
			res = 0;
		}
		
        return res;
	}
	
}
