//$Id$
package library;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LibraryGetBookById {
	
	public static String getBook(String bookid) throws IOException {
		
		Library lib = new Library();
		int row_number = RowNumber.getBookById(bookid);
		
		if(row_number == 0) {
			return null;
		}
		
		FileInputStream file = new FileInputStream("/home/local/ZOHOCORP/vinay-pt4139/Downloads/books.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(file);
		DataFormatter dataFormatter = new DataFormatter();
        Iterator<Sheet> sheets = book.sheetIterator();
		
		while(sheets.hasNext()) {
        	Sheet sh = sheets.next();
        	Iterator<Row> iterator =  sh.iterator();
        	
        	while(iterator.hasNext()) {	
        		Row row = iterator.next();
        		Iterator<Cell> cellIterator = row.iterator();
        		int count = 1;
        		
        		if(row.getRowNum() == row_number) {
        			while (cellIterator.hasNext()) {
    					Cell cell = cellIterator.next();
    					String cellValue = dataFormatter.formatCellValue(cell);
    					
    					if(count == 1) {
    						lib.setBookId(cellValue);
    					}
    					
    					if(count == 2) {
    						lib.setName(cellValue);
    					}
    					
    					if(count == 3) {
    						lib.setAuthor(cellValue);
    					}
    					
    					if(count == 4) {
    						lib.setTitle(cellValue);
    					}
    					
    					if(count == 5) {
    						lib.setEdition(cellValue);
    					}
    					
    					if(count == 6) {
    						lib.setPageCount(cellValue);
    					}
    					
    					if(count == 7) {
    						lib.setPublisher(cellValue);
    					}
    					
    					if(count == 8) {
    						lib.setPublishedDate(cellValue);
    					}
    					
    					if(count ==9) {
    						lib.setRate(cellValue);
    					}

    					count++;
    				}
        		}
        		
        	}
        }
		
		ObjectMapper obj = new ObjectMapper();
		String json =  obj.writeValueAsString(lib);
		return json;
	}
}
