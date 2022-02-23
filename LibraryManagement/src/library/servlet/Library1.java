package library.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import library.Library;
import library.LibraryAddBook;
import library.LibraryGetAllBook;

@WebServlet("/Library1")
public class Library1 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageid = Integer.parseInt(request.getParameter("page"));
		int per_page = Integer.parseInt(request.getParameter("per_page"));
		
		List<String> list = LibraryGetAllBook.getBooks(pageid,per_page);
		
		PrintWriter out = response.getWriter();
		
		if(list.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			out.println(list);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String data = "";   
	    StringBuilder builder = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    
	    while ((line = reader.readLine()) != null) {
	        builder.append(line);
	    }
	    data = builder.toString();
	    
	    JSONObject object = new JSONObject(data); 
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    Library lib = objectMapper.readValue(object.toString(),Library.class);
		
	    int status=0;
	    
		try {
			status = LibraryAddBook.addBook(lib);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		if(status == 1) {
			out.println("Successfully added");
			response.setStatus(HttpServletResponse.SC_CREATED);
		}
		else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
