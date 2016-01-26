package com.bqq.util;

public class TodoPage {
	private String whoCreate;	//¥¥Ω®»ÀÍ«≥∆
	private int startIndex;
	private int pageNumber;
	
	public TodoPage(String whoCreate, int startIndex, int pageNumber) {
		super();
		this.whoCreate = whoCreate;
		this.startIndex = startIndex;
		this.pageNumber = pageNumber;
	}
	
	@Override
	public String toString() {
		return "TodoPage [whoCreate=" + whoCreate + ", startIndex=" + startIndex + ", pageNumber=" + pageNumber + "]";
	}



	public String getWhoCreate() {
		return whoCreate;
	}
	
	public void setWhoCreate(String whoCreate) {
		this.whoCreate = whoCreate;
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
