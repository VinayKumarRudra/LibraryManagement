package library.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import library.Library;
import library.LibraryDeleteBook;
import library.LibraryGetBookById;
import library.LibraryUpdateBook;
import library.Response;

@WebServlet("/Library2")
public class Library2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path =request.getRequestURI().substring(request.getContextPath().length());
		String[] pathsegment = path.split("/");
		String book_id = pathsegment[pathsegment.length-1];
		
		
		String reqbook;
		try {
			reqbook = LibraryGetBookById.getBook(book_id);
			
			PrintWriter out = response.getWriter();
			if(reqbook == null) {
				Response res = new Response();
				res.setStatuscode(204);
				res.setField("book id");
				res.setMessage("book doesn't exist");
				res.setStatus("204 No Content");
				
				ObjectMapper obj = new ObjectMapper();
				String json =  obj.writeValueAsString(res);
				
				out.println(json);
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				out.println(reqbook);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

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
	         value = object.getString (key);
	      }
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    Library lib = objectMapper.readValue(object.toString(),Library.class);
	    
	    
		int status;
		try {
			if(value.isEmpty()) {
				Response res = new Response();
				res.setStatuscode(400);
				res.setField(key);
				res.setMessage("please enter value for field");
				res.setStatus("400 Bad request");
				
				ObjectMapper obj = new ObjectMapper();
				String json =  obj.writeValueAsString(res);
				PrintWriter out = response.getWriter();
				out.println(json);
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	
			}else {
				status = LibraryUpdateBook.updateBook(book_id,key,lib);
				PrintWriter out = response.getWriter();
				 if(status ==1 ) {
					Response res = new Response();
					res.setStatuscode(200);
					res.setField("no issues");
					res.setMessage("Successfully updated");
					res.setStatus("200 OK request");
					
					ObjectMapper obj = new ObjectMapper();
					String json =  obj.writeValueAsString(res);
					
					out.println(json);
					response.setStatus(HttpServletResponse.SC_OK);
				}  else {
					Response res = new Response();
					res.setStatuscode(204);
					res.setField("book id");
					res.setMessage("book doesn't exist");
					res.setStatus("204 No Content");
					
					ObjectMapper obj = new ObjectMapper();
					String json =  obj.writeValueAsString(res);
					
					out.println(json);
					//response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path =request.getRequestURI().substring(request.getContextPath().length());
		String[] pathsegment = path.split("/");
		String book_id = pathsegment[pathsegment.length-1];
		
		int status;
		try {
			status = LibraryDeleteBook.deleteBook(book_id);
			
			PrintWriter out = response.getWriter();
			if(status == 1) {
				Response res = new Response();
				res.setStatuscode(200);
				res.setField("no issues");
				res.setMessage("Successfully deleted");
				res.setStatus("200 OK request");
				
				ObjectMapper obj = new ObjectMapper();
				String json =  obj.writeValueAsString(res);
				
				out.println(json);
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else {
				Response res = new Response();
				res.setStatuscode(204);
				res.setField("book id");
				res.setMessage("book doesn't exist");
				res.setStatus("204 No Content");
				
				ObjectMapper obj = new ObjectMapper();
				String json =  obj.writeValueAsString(res);
				
				out.println(json);
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
