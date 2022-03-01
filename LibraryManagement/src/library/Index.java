//$Id$
package library;

public class Index {
	public static int getIndex(Library lib,String key) {
		int value=0;
		
		switch(key) {
		
		case "bookId":value = 1;
		break;
		
		case "name":value = 2;
		
		break;
		
		case "author":value = 3;
		break;
		
		case "title":value = 4;
		break;
		
		case "edition":value = 5;
		break;
		
		case "publisher":value =7;
		break;
		
		case "publishedDate":value = 8;
		break;
		
		case "pageCount":value = 6;
		break;
		
		}
		return value;
	}
}
