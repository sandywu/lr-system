package com.bqq.web;

import java.util.Date;

public class News implements Comparable<News>{
	private Integer id;	//保存id
	private String flag;//保存标识符，todo,said,log
	private String whoCreate;//保存创建人昵称
	private String content;//内容
	private Date createTime;
	
	@Override
    public int compareTo(News o) {
		return o.getCreateTime().compareTo(this.createTime);
    }
	
	public News(Integer id, String flag, String whoCreate, String content,Date createTime) {
		super();
		this.id = id;
		this.flag = flag;
		this.whoCreate = whoCreate;
		this.content = content;
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getWhoCreate() {
		return whoCreate;
	}

	public void setWhoCreate(String whoCreate) {
		this.whoCreate = whoCreate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
