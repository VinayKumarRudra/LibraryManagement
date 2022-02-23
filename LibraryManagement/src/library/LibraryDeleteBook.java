//$Id$
package library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LibraryDeleteBook {

	public static int deleteBook(String book_id) throws IOException {
		
		int rownumber = RowNumber.getBookById(book_id);
		int status = 0;
		
		FileInputStream file = new FileInputStream("/home/local/ZOHOCORP/vinay-pt4139/Downloads/book1.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(file);
		DataFormatter dataFormatter = new DataFormatter();
        Sheet sheet = book.getSheetAt(0);
        
        int lastRowNum=sheet.getLastRowNum();
        
        if(rownumber>=1 && rownumber<lastRowNum) {
        	sheet.shiftRows(rownumber+1,lastRowNum, -1);
        	System.out.println("hai");
        	status =1;
        }
        if(rownumber == lastRowNum) {
        	Row row = sheet.getRow(rownumber);
        	if(row != null) {
        		sheet.removeRow(row);
        		status = 1;
        		System.out.println("hello");
        	}
        	
        }
        
        FileOutputStream outstream = new FileOutputStream("/home/local/ZOHOCORP/vinay-pt4139/Downloads/book1.xlsx");
		book.write(outstream);
		outstream.close();
		
		return status;
	}

}
