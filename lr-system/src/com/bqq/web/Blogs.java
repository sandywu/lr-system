package com.bqq.web;

public class Blogs {
	private Integer id;			//日记本信息的唯一标识符
	private String whoCreate;	//创建人昵称
	private String name;		//日记本名
	private int size;			//当前日记本的日记数量
	
	
	public Blogs(Integer id, String whoCreate, String name, int size) {
		super();
		this.id = id;
		this.whoCreate = whoCreate;
		this.name = name;
		this.size = size;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWhoCreate() {
		return whoCreate;
	}
	public void setWhoCreate(String whoCreate) {
		this.whoCreate = whoCreate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	

}
