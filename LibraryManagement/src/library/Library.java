//$Id$
package library;

import java.util.Date;

public class Library {
	private String book_id;
	private String name;
	private String author;
	private String title;
	private String edition;
	private String publisher;
	private String publishedDate;
	private String pageCount;
	//private String rate;
	
	public String getName() {
		return name;
	}
	public  void setName(String bookname) {
		name = bookname;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String published_date) {
		this.publishedDate = published_date;
	}
	
	/*public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}*/
	
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String cellValue) {
		this.pageCount = cellValue;
	}
	
	public String getBookId() {
		return book_id;
	}
	public void setBookId(String book_id) {
		this.book_id = book_id;
	}
	
}
