//$Id$
package library;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CheckNullAddBook {
	public static int check(Library lib, int cellvalue) throws IOException {
		
		int checkvalue = 1;
			
		if(cellvalue==0) {
			checkvalue=0;
		}
		
		if(lib.getName().equals("")) {
			checkvalue=0;
		}
		
		if(lib.getAuthor().equals("")) {
			checkvalue=0;
		}
		
		if(lib.getTitle().equals("")) {
			checkvalue=0;
		}
		
		if(lib.getEdition().equals("")) {
			checkvalue=0;
		}
		
		if(lib.getPageCount().equals("")) {
			checkvalue=0;
		}
		
		if(lib.getPublisher().equals("")) {
			checkvalue=0;
		}
		
		if(lib.getPublishedDate().equals("")) {
			checkvalue=0;
		}
		
		if(lib.getRate().equals("")) {
			checkvalue=0;
		}
		
		
		return checkvalue;
	}
}
