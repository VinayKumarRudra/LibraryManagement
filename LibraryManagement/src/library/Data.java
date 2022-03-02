//$Id$
package library;

public class Data {
	private int page;
	private int per_page;
	private Boolean more_records;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPer_page() {
		return per_page;
	}
	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}

	public Boolean getMore_records() {
		return more_records;
	}
	public void setMore_records(Boolean more_records) {
		this.more_records = more_records;
	}
	
	
}
