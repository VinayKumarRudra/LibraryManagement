package library.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import library.Library;
import library.LibraryAddBook;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
		
		try {
			LibraryAddBook.addBook(lib);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
