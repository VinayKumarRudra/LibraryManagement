//$Id$
package library;

import library.Library;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GetBookList {
	public static List map_book = new ArrayList();
	public static int row_number;
	
	public static void getBookList() throws IOException {
		
		Library lib = new Library();
		
		int num = 1;
		
        FileInputStream file = new FileInputStream("/home/local/ZOHOCORP/vinay-pt4139/Downloads/books.xlsx");
        XSSFWorkbook book = new XSSFWorkbook(file);
        
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Sheet> sheets = book.sheetIterator();
        
        while(sheets.hasNext()) {
        	Sheet sh = sheets.next();
       
        	row_number = sh.getLastRowNum()+1;
			int column_number = sh.getRow(0).getLastCellNum();
			
			Iterator<Row> iterator =  sh.iterator();
			while(iterator.hasNext()) {
				Row row = iterator.next();
				if(row.getRowNum() == 0) {
					continue;	
				}
				Iterator<Cell> cellIterator = row.iterator();
				int count = 1;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String cellValue = dataFormatter.formatCellValue(cell);
					
					if(count == 1) {
						lib.setName(cellValue);
					}
					
					if(count == 2) {
						lib.setAuthor(cellValue);
					}
					
					if(count == 3) {
						lib.setTitle(cellValue);
					}
					
					if(count == 4) {
						lib.setEdition(cellValue);
					}
					
					if(count == 5) {
						lib.setPageCount(cellValue);
					}
					
					if(count == 6) {
						lib.setPublisher(cellValue);
					}
					
					if(count == 7) {
						lib.setPublishedDate(cellValue);
					}
					
					if(count == 8) {
						lib.setRate(cellValue);
					}

					count++;
				}
				
				ObjectMapper obj = new ObjectMapper();
				String json =  obj.writeValueAsString(lib);
				
				map_book.add(json);
			}
			
        }
	}
}
