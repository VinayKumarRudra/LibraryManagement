package library.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import library.Library;
import library.LibraryUpdateBook;

/**
 * Servlet implementation class UpdateBook
 */
@WebServlet("/UpdateBook")
public class UpdateBook extends HttpServlet {
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path =request.getRequestURI().substring(request.getContextPath().length());
		String[] pathsegment = path.split("/");
		String book_id = pathsegment[pathsegment.length-1];
		
		String data = "";   
	    StringBuilder builder = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    
	    while ((line = reader.readLine()) != null) {
	        builder.append(line);
	    }
	    data = builder.toString();
	    
	    JSONObject object = new JSONObject(data); 
	    Iterator<String> k = object.keys();
	    String key = "";
	    String value = "";
	    while(k.hasNext()) {
	         key = k.next();
	      }
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    Library lib = objectMapper.readValue(object.toString(),Library.class);
	    
		LibraryUpdateBook.updateBook(book_id,key,lib);
	}

}
