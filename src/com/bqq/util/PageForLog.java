package com.bqq.util;

public class PageForLog {
	private String whoCreate;	//创建人昵称
	private String blogName;	//日记本名字
	private int startIndex;
	private int pageNumber;
	
	
	public PageForLog(String whoCreate, String blogName, int startIndex,
			int pageNumber) {
		super();
		this.whoCreate = whoCreate;
		this.blogName = blogName;
		this.startIndex = startIndex;
		this.pageNumber = pageNumber;
	}
	
	public String getWhoCreate() {
		return whoCreate;
	}
	public void setWhoCreate(String whoCreate) {
		this.whoCreate = whoCreate;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
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
