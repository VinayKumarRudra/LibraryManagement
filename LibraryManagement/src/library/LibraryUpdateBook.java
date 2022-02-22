//$Id$
package library;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LibraryUpdateBook {

	public static void updateBook(String book_id,String key,Library lib) throws IOException {
		
		int row_number = RowNumber.getBookById(book_id);
		System.out.println("row : "+row_number);
		if(row_number == 0) {
			//return null;
		}
		
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
        		if(row.getRowNum() == row_number) {
        			while (cellIterator.hasNext()) {
        				Cell cell = cellIterator.next();
        				int index = ColumnNumber.getColumnNumber(row_number, key);
        				System.out.println("index : "+index);
        				switch(index) {
        				
        				case 1:cell.setCellValue(lib.getName());
        						System.out.println(lib.getName());
        				break;
        				
        				case 2:cell.setCellValue(lib.getAuthor());
        				System.out.println("author : "+lib.getAuthor());
        				break;
        				
        				case 3:cell.setCellValue(lib.getTitle());
        				System.out.println(lib.getTitle());
        				break;
        				
        				case 4:cell.setCellValue(lib.getEdition());
        				break;
        				
        				case 5:cell.setCellValue(lib.getPageCount());
        				break;
        				
        				case 6:cell.setCellValue(lib.getPublisher());
        				break;
        				
        				case 7:cell.setCellValue(lib.getPublishedDate());
        				break;
        				
        				case 8:cell.setCellValue(lib.getRate());
        				break;
        				
        				}
        			}
        		}
        	}
        }
        Sheet sheet = book.getSheetAt(0);
        Cell cell2Update = sheet.getRow(1).getCell(4);
        cell2Update.setCellValue(49);
        
        FileOutputStream outstream = new FileOutputStream("/home/local/ZOHOCORP/vinay-pt4139/Downloads/book1.xlsx");
		book.write(outstream);
		outstream.close();
	}
	
}
