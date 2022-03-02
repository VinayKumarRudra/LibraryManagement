//$Id$
package library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LibraryGetAllBook {
	public static List map_data = new ArrayList();
	
	public static List<String> getBooks(int pageid,int per_page) throws Exception {
		
		Data data = new Data();
		
		/*int p = pageid;
		int pp = per_page;
		String page = String.valueOf(p);
		String perpage = String.valueOf(pp);*/
		data.setPage(pageid);
		data.setPer_page(per_page);
		
		List<String> list=new ArrayList<String>();  
		
		GetBookList.getBookList();
		int totalrecords = GetBookList.row_number;
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
		
		if(pagenumber < totalpages) {
			data.setMore_records(true);
		} else {
			data.setMore_records(false);
		}
		
		
		
		if(pagenumber <= totalpages) {
			for(int i=pageid-1;i<pageid+per_page-1;i++) {
				if(i<totalrecords) {
					list.add((String) GetBookList.map_book.get(i));
				}
			} 
			
		} else{
			 
		}
		ObjectMapper obj = new ObjectMapper();
		String json =  obj.writeValueAsString(data);
		map_data.add(json);
		
		System.out.println(map_data);
		
		return list;
	}
	
	public static List<String> getData() {
		List<String> list=new ArrayList<String>();
		list.add((String) map_data.get(map_data.size()-1));
		return list;
	}
}
