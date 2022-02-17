package library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryGetAllBook;
/**
 * Servlet implementation class GetAllBooks
 */
@WebServlet("/GetAllBooks")
public class GetAllBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
