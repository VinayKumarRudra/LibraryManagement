package library.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import library.GetBookList;
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
		
		GetBookList.getBookList();
		
		PrintWriter out = response.getWriter();
		boolean over = false;
		
		
		int pageid = Integer.parseInt(request.getParameter("page"));
		int per_page = Integer.parseInt(request.getParameter("per_page"));
		
		int totalrecords = GetBookList.row_number-1;
		int totalpages = 0;
		int pagenumber = pageid;
		
		totalpages = totalrecords/per_page;
		if(totalrecords > totalpages*per_page) {
			totalpages = totalpages+1;
		}
		
		
		if(pageid == 1) {
			
		}
		else {
			pageid = pageid-1;
			pageid = pageid*per_page+1;
		}

		if(pagenumber <= totalpages) {
			for(int i=pageid-1;i<pageid+per_page-1;i++) {
				if(i<totalrecords) {
					out.println(GetBookList.map_book.get(i));
				}
			} 
		} else{
			 response.setStatus(HttpServletResponse.SC_NO_CONTENT);
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
