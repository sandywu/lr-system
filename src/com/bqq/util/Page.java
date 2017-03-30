package com.bqq.util;

public class Page {
	private int startIndex;
	private int pageNumber;
	
	public Page(int startIndex, int pageNumber) {
		super();
		this.startIndex = startIndex;
		this.pageNumber = pageNumber;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
}
