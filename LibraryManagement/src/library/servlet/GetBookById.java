package library.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryGetBookById;

/**
 * Servlet implementation class GetBookById
 */
@WebServlet("/GetBookById")
public class GetBookById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path =request.getRequestURI().substring(request.getContextPath().length());
		String[] pathsegment = path.split("/");
		String book_id = pathsegment[pathsegment.length-1];
		
		
		String reqbook = LibraryGetBookById.getBook(book_id); 
		
		PrintWriter out = response.getWriter();
		if(reqbook == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			out.println(reqbook);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
