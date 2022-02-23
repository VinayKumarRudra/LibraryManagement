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
		
		FileInputStream file = new FileInputStream("/home/local/ZOHOCORP/vinay-pt4139/Downloads/book1.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(file);
		DataFormatter dataFormatter = new DataFormatter();
        Sheet sheet = book.getSheetAt(0);
        
        int rownumber =  RowNumber.getBookById(book_id);
        int index = ColumnNumber.getColumnNumber(rownumber,key);
        String value = UpdateLibValue.getValue(index,lib);
        
        Cell cell2Update = sheet.getRow(rownumber).getCell(index);
        cell2Update.setCellValue(value);
        
        FileOutputStream outstream = new FileOutputStream("/home/local/ZOHOCORP/vinay-pt4139/Downloads/book1.xlsx");
		book.write(outstream);
		outstream.close();
	}
	
}
