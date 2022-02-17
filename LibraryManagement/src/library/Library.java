//$Id$
package library;

public class Library {
	private String name;
	private String author;
	private String title;
	private String edition;
	private String publisher;
	private String published_date;
	private String no_of_pages,rate;
	
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
		return published_date;
	}
	public void setPublishedDate(String published_date) {
		this.published_date = published_date;
	}
	
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public String getPageCount() {
		return no_of_pages;
	}
	public void setPageCount(String cellValue) {
		this.no_of_pages = cellValue;
	}
}
