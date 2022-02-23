//$Id$
package library;

public class UpdateLibValue {

	public static String getValue(int index,Library lib) {
		
		String value = "";
		
		if(index == 0) {
			value = lib.getBookId();
		}
		
		if(index == 1) {
			value = lib.getName();
		}
		
		if(index == 2) {
			value = lib.getAuthor();
		}
		
		if(index == 3) {
			value = lib.getTitle();
		}
		
		if(index == 4) {
			value = lib.getEdition();
		}
		
		if(index == 5) {
			value = lib.getPageCount();
		}
		
		if(index == 6) {
			value = lib.getPublisher();
		}
		
		if(index == 7) {
			value = lib.getPublishedDate();
		}
		
		if(index == 8) {
			value = lib.getRate();
		}
		
		return value;
	}

}
