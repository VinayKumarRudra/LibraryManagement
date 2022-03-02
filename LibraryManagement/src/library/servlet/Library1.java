package library.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
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
import library.LibraryGetBookById;
import library.Response;

@WebServlet("/Library1")
public class Library1 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = request.getParameter("page");
		String perpage = request.getParameter("per_page");
		
		int pageid =0;
		int per_page = 0;
		if(page == null && perpage == null) {
			pageid = 1;
			per_page = 5;
		} else if(page == null) {
			pageid = 1;
			per_page = Integer.parseInt(perpage);
		} else if(perpage == null) {
			pageid = Integer.parseInt(page);
			per_page = 5;
		} else {
			pageid = Integer.parseInt(page);
			per_page = Integer.parseInt(perpage);
		}
		
		
		List<String> list;
		List<String> data;
		try {
			list = LibraryGetAllBook.getBooks(pageid,per_page);
			data = LibraryGetAllBook.getData();
			PrintWriter out = response.getWriter();
			
			if(list.isEmpty()) {
				Response res = new Response();
				res.setStatuscode(204);
				res.setField("page or perpage");
				res.setMessage("page doesn't exist");
				res.setStatus("204 No Content");
				
				ObjectMapper obj = new ObjectMapper();
				String json =  obj.writeValueAsString(res);
				
				out.println(json);
				
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				out.println(list);
				out.println(data);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postoperation = 1;
		String missingfield="";
		
		String data = "";   
	    StringBuilder builder = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    
	    while ((line = reader.readLine()) != null) {
	        builder.append(line);
	    }
	    data = builder.toString();
	    
	    JSONObject object = new JSONObject(data); 
	    
	    List mandatoryfield = new ArrayList();
	    mandatoryfield.add("name");
	    mandatoryfield.add("author");
	    mandatoryfield.add("publisher");
	    
	    List postkeys = new ArrayList();
	    Iterator<String> k = object.keys();
	    String key = "";
	    while(k.hasNext()) {
	         key = k.next();
	         postkeys.add(key);
	      }
	    
	    for(int i=0;i<mandatoryfield.size();i++) {
	    	System.out.println(mandatoryfield.get(i));
	    	if(postkeys.contains(mandatoryfield.get(i))) {
	    		
	    	} else {
	    		missingfield+= mandatoryfield.get(i)+",";
	    		postoperation = 0;
	    	}
	    }
	    
	    
	    if(postoperation == 1) {
	    	ObjectMapper objectMapper = new ObjectMapper();
		    Library lib = objectMapper.readValue(object.toString(),Library.class);
			int book_id=0;
		    int status=0;
		    List post;
			try {
				post = LibraryAddBook.addBook(lib);
				status = (int) post.get(1);
				book_id = (int) post.get(0);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			PrintWriter out = response.getWriter();
			if(status == 1) {
				
				String reqbook;
				try {
					String str = String.valueOf(book_id);
					reqbook = LibraryGetBookById.getBook(str);
					
					PrintWriter outp = response.getWriter();
					if(reqbook == null) {
						response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					} else {
						outp.println(reqbook);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				response.setStatus(HttpServletResponse.SC_CREATED);
			}
			else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
	    } else {
	    	Response res = new Response();
			res.setStatuscode(400);
			missingfield = missingfield.substring(0,missingfield.length()-1);
			res.setField(missingfield);
			res.setMessage("please fill mandatory fields");
			res.setStatus("400 Bad request");
			
			ObjectMapper obj = new ObjectMapper();
			String json =  obj.writeValueAsString(res);
			PrintWriter out = response.getWriter();
			out.println(json);
	    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    }
	    
	}

}
