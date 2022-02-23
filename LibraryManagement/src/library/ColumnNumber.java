//$Id$
package library;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ColumnNumber {
	public static int getColumnNumber(int row_number,String key) throws IOException {
		int index=0;
		
        				
        if(key.equals("name")) {
        	index = 1;
        }
        				
        if(key.equals("author")) {
        	index = 2;
        }
        				
        if(key.equals("title")) {
        	index = 3;
        }
        				
        if(key.equals("edition")) {
        	index = 4;
        }
        
        if(key.equals("pageCount")) {
        	index = 5;
        }
        
        if(key.equals("publisher")) {
        	index = 6;
        }
        
        if(key.equals("publishedDate")) {
        	index = 7;
        }
        
        if(key.equals("rate")) {
        	index = 8;
        }
		
		
		
		return index;
		
	}
}
