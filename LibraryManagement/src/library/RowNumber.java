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

public class RowNumber {
	public static int getBookById(String bookid) throws IOException {
		Library lib = new Library();
		int row_number = 0;
		
		
		FileInputStream file = new FileInputStream("/home/local/ZOHOCORP/vinay-pt4139/Downloads/book1.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(file);
		
		DataFormatter dataFormatter = new DataFormatter();
        Iterator<Sheet> sheets = book.sheetIterator();
        
        while(sheets.hasNext()) {
        	Sheet sh = sheets.next();
        	Iterator<Row> iterator =  sh.iterator();
        	while(iterator.hasNext()) {
        		Row row = iterator.next();
        		Iterator<Cell> cellIterator = row.iterator();
        		while (cellIterator.hasNext()) {
        			
        			Cell cell = cellIterator.next();
        			String book_id = (String) dataFormatter.formatCellValue(cell);
        			
        			
        			if(book_id.equals(bookid)) {
        				row_number = row.getRowNum();
        			}
        		}
        	}
        }
        
		return row_number;
        
	}
}
