//$Id$
package library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryGetAllBook {
public static List<String> getBooks(int pageid,int per_page) throws IOException {
		
		List<String> list=new ArrayList<String>();  
		
		GetBookList.getBookList();
		int totalrecords = GetBookList.row_number-1;
		int totalpages = 0;
		int pagenumber = pageid;
		
		totalpages = totalrecords/per_page;
		if(totalrecords > totalpages*per_page) {
			totalpages = totalpages+1;
		}
		
		if(pageid == 1) {
			
		} else {
			pageid = pageid-1;
			pageid = pageid*per_page+1;
		}
		
		if(pagenumber <= totalpages) {
			for(int i=pageid-1;i<pageid+per_page-1;i++) {
				if(i<totalrecords) {
					list.add((String) GetBookList.map_book.get(i));
				}
			} 
			
		} else{
			 
		}
		
		return list;
	}
}
